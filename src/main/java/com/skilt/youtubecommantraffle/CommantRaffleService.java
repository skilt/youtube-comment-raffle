package com.skilt.youtubecommantraffle;

import com.google.api.services.youtube.model.Comment;
import com.google.api.services.youtube.model.CommentSnippet;
import com.google.api.services.youtube.model.CommentThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class CommantRaffleService {

  @Autowired
  private YoutubeCommantRepository youtubeCommantRepository;

  public List<CommentSnippet> raffleComment(String videoId, int count) throws GeneralSecurityException, IOException {
    // repository의 댓글 요청
    Random random = new Random();
    List<CommentSnippet>list = new LinkedList<>();
    log.info("videoId: {}, count: {}",videoId,count);
    for(int i = 0; i<count; i++){
      list.add(youtubeCommantRepository.getComments(videoId,random.nextInt(100)+1 , random.nextInt(10)+1));
    }
    return list;
  }
}
