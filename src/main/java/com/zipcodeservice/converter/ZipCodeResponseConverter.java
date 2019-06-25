package com.zipcodeservice.converter;

import com.zipcodeservice.dto.ZipCode;
import com.zipcodeservice.dto.ZipCodeRangeResponse;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

public class ZipCodeResponseConverter {

  public static ResponseEntity<ZipCodeRangeResponse> fromSet(Set<ZipCode> zipCodes) {
    ZipCodeRangeResponse zipCodeRangeResponse = new ZipCodeRangeResponse();
    if (CollectionUtils.isEmpty(zipCodes)) {
      return ResponseEntity.ok(zipCodeRangeResponse);
    }

    zipCodeRangeResponse.setZipCodeRanges(zipCodes.stream().map(ZipCode::toString).collect(Collectors.joining(" ")));
    return ResponseEntity
        .ok(zipCodeRangeResponse);
  }

}
