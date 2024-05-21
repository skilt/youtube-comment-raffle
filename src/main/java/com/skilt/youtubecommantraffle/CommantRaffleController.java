package com.skilt.youtubecommantraffle;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Slf4j
@Controller
public class CommantRaffleController {

  @Autowired
  private CommantRaffleService commantRaffleService;

  @GetMapping("/api/{videoId}")
  @ResponseBody
  public void commantRaffle(@PathVariable String videoId, @RequestParam int count) throws GeneralSecurityException, IOException {
    commantRaffleService.raffleComment(videoId,count);
    log.info("videoId: {}, count: {}",videoId,count);
  }
}