package com.zipcodeservice.services;

import com.zipcodeservice.dto.ZipCode;
import java.util.List;

public interface ZipCodesConverter {

  /**
   * Convert zip code ranges Strigng to list. Input param structure : [94133,94133]
   * [94200,94299] [94226,94399] or method will throw IllegalArgumentException
   */
  List<ZipCode> convert(String zipcodeRanges);
}
