package com.skilt.youtubecommantraffle;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.CommentSnippet;
import com.google.api.services.youtube.model.CommentThread;
import com.google.api.services.youtube.model.CommentThreadListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.*;

@Slf4j
@Repository
public class YoutubeCommantRepository {
  @Value("${youtube.api.key}")
  private String apiKey;

  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
  private static final String APPLICATION_NAME = "youtube_comment_raffle";

  public static YouTube getService() throws GeneralSecurityException, IOException {
    final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
    return new YouTube.Builder(httpTransport, JSON_FACTORY, null)
        .setApplicationName(APPLICATION_NAME)
        .build();
  }

  public void getComments(String videoId) throws GeneralSecurityException, IOException, GoogleJsonResponseException {
    YouTube youtube = getService();
    // Define and execute the API request
    YouTube.CommentThreads.List request = youtube.commentThreads()
        .list(Collections.singletonList("snippet"));
    CommentThreadListResponse response = request.setKey(apiKey)
        .setVideoId(videoId)
        .setMaxResults(100l)
        .execute();

    List<CommentThread> videoComments = response.getItems();
    System.out.println(videoComments.size());
    for(CommentThread videoComment : videoComments){
      CommentSnippet snippet = videoComment.getSnippet().getTopLevelComment().getSnippet();
      System.out.println("UserName: " + snippet.getAuthorDisplayName());
      System.out.println("comment: " + snippet.getTextDisplay());
      System.out.println("-----------------------------------------------------");
    }
    log.info("videoId: {}",videoId);
  }
}
