package org.projectzion.game.utils.setup;

import lombok.SneakyThrows;
import org.projectzion.game.configs.TargetSystemConfig;
import org.projectzion.game.persitence.entities.TargetSystem;
import org.projectzion.game.persitence.repositories.TargetSystemRepository;
import org.projectzion.game.services.KeyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

//TODO build a base class for these setups
//  ich hab mich doch nicht jahre lang euren spielen ausgesetzt um die wege hinter mir nicht zu pflastern...
//  ich kann mir eure manipulative scheiße nimmer reinziehen, ihr seid verfault.
@Component
public class SetupTargetsystem implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    TargetSystemConfig targetSystemConfig;

    @Autowired
    KeyValueService keyValueService;

    @Autowired
    TargetSystemRepository targetSystemRepository;

    public static String KV_TARGET_SYTEM_SETUP_DONE = "KV_TARGET_SYTEM_SETUP_DONE";

    private boolean isSetupDone() throws Exception {
        return keyValueService.read(KV_TARGET_SYTEM_SETUP_DONE, Date.class) != null;
    }

    @Transactional
    void saveSetupIsDone() throws Exception{
        Date now = new Date();
        keyValueService.save(KV_TARGET_SYTEM_SETUP_DONE,now);
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(isSetupDone()){
            return;
        }

        TargetSystem targetSystem = new TargetSystem();
        targetSystem.setId(targetSystemConfig.getId());
        targetSystem.setName(targetSystemConfig.getName());

        targetSystem = targetSystemRepository.save(targetSystem);

        saveSetupIsDone();
    }
}
