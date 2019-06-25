package com.zipcodeservice.controller;

import com.zipcodeservice.converter.ZipCodeResponseConverter;
import com.zipcodeservice.services.ZipCodeMerger;
import com.zipcodeservice.services.ZipCodesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZipCodesController {

  @Autowired
  private ZipCodeMerger zipCodeMerger;
  @Autowired
  private ZipCodesConverter zipCodesConverter;

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(path = "/process-zipcodes")
  public ResponseEntity index(@RequestBody String RequestBody) {
    return ZipCodeResponseConverter.fromSet(
        zipCodeMerger.mergeZipCodes(
            zipCodesConverter.convert(RequestBody)
        ));
  }

}
