package org.demo.mykajtools.mydomain;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class MyEventClient { //extends AbstractClient<MyKey, MyValue> {

  @Getter
  private final String defaultTopic = "TOPIC-NAME";

  @Getter
  public List<String> availableTopics = Lists.newArrayList(defaultTopic,
      "OTHER-TOPIC-NAME");

  @Getter
  private final String resourcesPath = "events/my-domain";

  public MyEventClient() {
//    super(MyKey.class, MyValue.class);
  }
}
