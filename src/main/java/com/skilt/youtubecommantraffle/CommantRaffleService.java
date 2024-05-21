package com.skilt.youtubecommantraffle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Slf4j
@Service
public class CommantRaffleService {

  @Autowired
  private YoutubeCommantRepository youtubeCommantRepository;
  public void raffleComment(String videoId, int count) throws GeneralSecurityException, IOException {
    // repository의 댓글 요청
    log.info("videoId: {}, count: {}",videoId,count);
    youtubeCommantRepository.getComments(videoId);
  }
}
