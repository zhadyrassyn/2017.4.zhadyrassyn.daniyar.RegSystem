package kz.zhadyrassyn.regsystem.stand.register_stand_impl.scheduler;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.scheduling.ExecutionPool;
import kz.greetgo.scheduling.Scheduler;
import kz.greetgo.scheduling.Task;
import kz.greetgo.scheduling.TaskCollector;

import java.util.List;

@Bean
public class MainScheduler {

    private Scheduler scheduler = null;

    public void startSchedulers(MyTask myTask){
        if(scheduler != null) return;

        String configDir = System.getProperty("user.home")+"/regsystem.d";
        TaskCollector taskCollector = new TaskCollector(configDir);

        taskCollector.collect(myTask);

        List<Task> tasks = taskCollector.getTasks();

        scheduler = new Scheduler(tasks, ExecutionPool.poolsForTasks(tasks));

        scheduler.startup();
    }
}
