package com.skilt.youtubecommantraffle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommantRaffleService {

  @Autowired
  private YoutubeCommantRepository youtubeCommantRepository;
  public void raffleComment(String videoId, int count){
    // repository의 댓글 요청
    youtubeCommantRepository.getComments(videoId);
  }
}
