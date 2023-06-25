package com.andreypshenichnyj.iate.administrator.service.threads;

import com.andreypshenichnyj.iate.administrator.dao.logs.LogsDAO;
import com.andreypshenichnyj.iate.administrator.dao.thread_scripts.Thread_scriptDAO;
import com.andreypshenichnyj.iate.administrator.entity.Logs;
import com.andreypshenichnyj.iate.administrator.entity.Thread_scripts;
import com.andreypshenichnyj.iate.administrator.terminal.Connector;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Transactional
@Service
public class RunningThreadAdministratorImpl implements RunningThreadAdministrator{

    @Autowired
    private ApplicationContext applicationContext;

    private static Map<Integer, ScheduledExecutorService> threads = new HashMap<>();

    @Autowired
    private Thread_scriptDAO thread_scriptDAO;

    public void addScript(Thread_scripts thread_script){
        thread_script.setDate(new Date());
        thread_scriptDAO.addThread_script(thread_script);
        ThreadInfo threadInfo = new ThreadInfo(thread_script);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(threadInfo);
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleWithFixedDelay(threadInfo, 0, thread_script.getTime_minutes(), TimeUnit.MINUTES);
        threads.put(thread_script.getThread_script_id(), service);
    }

    public void deleteScript(Thread_scripts thread_script){
        ScheduledExecutorService service = threads.get(thread_script.getThread_script_id());
        service.shutdown();
        thread_script.setDate(null);
        thread_scriptDAO.addThread_script(thread_script);
        threads.remove(thread_script.getThread_script_id());
    }


    public class ThreadInfo implements Runnable{

        //  Database credentials
        static final String DB_URL = "jdbc:postgresql://localhost:5432/Administration_db";
        static final String USER = "postgres";
        static final String PASS = "5432";

        private Thread_scripts thread_script;

        public ThreadInfo(Thread_scripts thread_script) {
            this.thread_script = thread_script;
        }

        @Override
        public void run() {

            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            Connection connection = null;

            try {
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Connector connector = new Connector(thread_script.getGenerated_script());
            BufferedReader inputReader = connector.getInputReader();
            BufferedReader errorReader = connector.getInputReader();

            StringBuilder sb = new StringBuilder();
            StringBuilder sb_2 = new StringBuilder();
            String str = null;
            String str_2 = null;
            try {
                while ((str = inputReader.readLine()) != null) {
                    sb.append(str);
                }
                while ((str_2 = errorReader.readLine()) != null){
                    sb_2.append(str_2);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            str = sb.toString();
            str_2 = sb_2.toString();

            String insertLog = "insert into logs(work_room_id, thread_script_id, launch_time, log_text)" +
                    " VALUES (" +
                    thread_script.getWork_room().getWork_room_id() + ", " +
                    thread_script.getThread_script_id() + ", " +
                    "'" + new Date() + "', " +
                    "'" + str + "')";

            Statement statement = null;
            try {
                statement = connection.createStatement();
                statement.execute(insertLog);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
