package com.zipcodeservice.controller;

import static com.zipcodeservice.util.FixtureUtil.fixture;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ZipCodesControllerIntegrationTest {

  public static final String WRONG_PARAM =
      "fixtures/wrong_param_spaces_before.json";
  public static final String FOUR_PARAMS_WITH_ONE_OUTPUT =
      "fixtures/four_params_with_duplicates_with_one_output.json";
  public static final String THREE_PARAMS_WITH_THREE_OUTPUT =
      "fixtures/three_params_three_output.json";
  public static final String ONE_PARAM_WITH_ONE_OUTPUT =
      "fixtures/one_param_with_one_output.json";
  public static final String THREE_PARAMS_WITH_TWO_OUTPUT =
      "fixtures/three_params_with_two_outputs.json";
  public static final String EMPTY_RANGE =
      "fixtures/empty_range.json";

  @LocalServerPort
  private int port;

  TestRestTemplate restTemplate = new TestRestTemplate();
  HttpHeaders headers = new HttpHeaders();

  @Test
  public void test_null_param() {
    ResponseEntity response = getResponseEntity(null);
    assertEquals(400, response.getStatusCode().value());
  }

  @Test
  public void test_wrong_param_spaces_before() {
    ResponseEntity response = getResponseEntity("[ 99120,99190]");
    assertEquals(400, response.getStatusCode().value());
    String expectedDto = fixture(WRONG_PARAM);
    assertEquals(expectedDto, response.getBody());
  }

  @Test
  public void test_wrong_param_spaces_after_first_range() {
    ResponseEntity response = getResponseEntity("[99120 ,99190]");
    assertEquals(400, response.getStatusCode().value());
    String expectedDto = fixture(WRONG_PARAM);
    assertEquals(expectedDto, response.getBody());
  }

  @Test
  public void test_wrong_param_spaces_before_second_range() {
    ResponseEntity response = getResponseEntity("[99120, 99190]");
    assertEquals(400, response.getStatusCode().value());
    String expectedDto = fixture(WRONG_PARAM);
    assertEquals(expectedDto, response.getBody());
  }

  @Test
  public void test_wrong_param_spaces_after_second_range() {
    ResponseEntity response = getResponseEntity("[99120,99190 ]");
    assertEquals(400, response.getStatusCode().value());
    String expectedDto = fixture(WRONG_PARAM);
    assertEquals(expectedDto, response.getBody());
  }

  @Test
  public void test_wrong_param_no_coma_and_no_spaces() {
    ResponseEntity response = getResponseEntity("[9912099190]");
    assertEquals(400, response.getStatusCode().value());
    String expectedDto = fixture(WRONG_PARAM);
    assertEquals(expectedDto, response.getBody());
  }

  @Test
  public void test_wrong_param_no_open_bracket() {
    ResponseEntity response = getResponseEntity("99120,99190]");
    assertEquals(400, response.getStatusCode().value());
    String expectedDto = fixture(WRONG_PARAM);
    assertEquals(expectedDto, response.getBody());
  }

  @Test
  public void test_wrong_param_no_close_bracket() {
    ResponseEntity response = getResponseEntity("[99120,99190");
    assertEquals(400, response.getStatusCode().value());
    String expectedDto = fixture(WRONG_PARAM);
    assertEquals(expectedDto, response.getBody());
  }

  @Test
  public void test_wrong_param_no_close_bracket_no_open_bracket() {
    ResponseEntity response = getResponseEntity("99120,99190");
    assertEquals(400, response.getStatusCode().value());
    String expectedDto = fixture(WRONG_PARAM);
    assertEquals(expectedDto, response.getBody());
  }

  @Test
  public void test_empty_param() {
    ResponseEntity response = getResponseEntity(" ");
    assertEquals(200, response.getStatusCode().value());
    String expectedDto = fixture(EMPTY_RANGE);
    assertEquals(expectedDto, response.getBody());
  }

  @Test
  public void test_3_params_with_2_output() {
    ResponseEntity response = getResponseEntity("[94133,94133] [94200,94299] [94226,94399]");
    assertEquals(200, response.getStatusCode().value());
    String expectedDto = fixture(THREE_PARAMS_WITH_TWO_OUTPUT);
    assertEquals(expectedDto, response.getBody());
  }

  @Test
  public void test_3_params_with_3_output() {
    ResponseEntity response = getResponseEntity("[94133,94133] [94200,94299] [94600,94699]");
    assertEquals(200, response.getStatusCode().value());
    String expectedDto = fixture(THREE_PARAMS_WITH_THREE_OUTPUT);
    assertEquals(expectedDto, response.getBody());
  }

  @Test
  public void test_1_params_with_1_output() {
    ResponseEntity response = getResponseEntity("[99123,99123]");
    assertEquals(200, response.getStatusCode().value());
    String expectedDto = fixture(ONE_PARAM_WITH_ONE_OUTPUT);
    assertEquals(expectedDto, response.getBody());
  }

  @Test
  public void test_4_params_with_duplicates_with_1_output() {
    ResponseEntity response = getResponseEntity("[99123,99190] [99123,99123] [99190,99190]");
    assertEquals(200, response.getStatusCode().value());
    String expectedDto = fixture(FOUR_PARAMS_WITH_ONE_OUTPUT);
    assertEquals(expectedDto, response.getBody());
  }

  @Test
  public void test_1_params_wrong_data() {
    ResponseEntity response = getResponseEntity("[9912,99190]");
    assertEquals(400, response.getStatusCode().value());
    String expectedDto = fixture(WRONG_PARAM);
    assertEquals(expectedDto, response.getBody());
  }

  @Test
  public void test_2_params_wrong_data() {
    ResponseEntity response = getResponseEntity("[94133 94133] [94200,94299]");
    assertEquals(400, response.getStatusCode().value());
    String expectedDto = fixture(WRONG_PARAM);
    assertEquals(expectedDto, response.getBody());
  }

  @Test
  public void test_1_param_wrong_data() {
    ResponseEntity response = getResponseEntity("[94133,]");
    assertEquals(400, response.getStatusCode().value());
    String expectedDto = fixture(WRONG_PARAM);
    assertEquals(expectedDto, response.getBody());
  }

  @Test
  public void test_1_param_wrong_zipcode_length_data() {
    ResponseEntity response = getResponseEntity("[94133,9413221] [94200,94299]");
    assertEquals(400, response.getStatusCode().value());
    String expectedDto = fixture(WRONG_PARAM);
    assertEquals(expectedDto, response.getBody());
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }

  private ResponseEntity getResponseEntity(String body) {
    HttpEntity<String> entity = new HttpEntity<>(body, headers);
    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/process-zipcodes"), HttpMethod.POST, entity, String.class);

    return response;
  }
}
