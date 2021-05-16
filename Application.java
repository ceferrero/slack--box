
package com.box.slack.box;

import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.json.internal.json_simple.parser.JSONParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class Application {
  @PostMapping("/event")
  public JSONObject challenge(@RequestBody String data) throws Exception {
    JSONObject returnJSON = new JSONObject();

    Object dataObj = new JSONParser().parse(data);
    JSONObject inputJSON = (JSONObject) dataObj;
    String challenge = (String) inputJSON.get("challenge");
    String type = (String) inputJSON.get("type");

    if (type.equals("url_verification")) {
      returnJSON.put("challenge", challenge);
    } else {
      System.err.println("Invalid input");
    }

    return returnJSON;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}