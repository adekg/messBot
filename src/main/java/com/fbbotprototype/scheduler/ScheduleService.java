package com.fbbotprototype.scheduler;

import com.fbbotprototype.services.FBService;
import com.fbbotprototype.services.XKomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduleService {

    private final XKomService xKomService;
    private final FBService fbService;
//    @Value("#{systemEnvironment['RECEIVER']}")
    private final String receiver = "Adrian Gałach";

    public ScheduleService(@Autowired XKomService xKomService, @Autowired FBService fbService) {
        this.xKomService = xKomService;
        this.fbService = fbService;
    }

    @Scheduled(cron = "* 0/5 * * * *")
    public void xKomSender() {
        xKomService.getGPUs();
        fbService.sendClipboardImageTo(receiver);
        xKomService.getHotShot();
        fbService.sendClipboardImageTo(receiver);
    }
}
