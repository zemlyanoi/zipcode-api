package com.zipcodeservice.services;

import com.zipcodeservice.dto.ZipCode;
import java.util.List;

public interface ZipCodesConverter {

  /**
   * Convert zip code ranges String to list.
   * @param  zipcodeRanges: [94133,94133] [94200,94299] [94226,94399]
   * @return sorted List<ZipCode>
   * @throws IllegalArgumentException
   */
  List<ZipCode> convert(String zipcodeRanges);
}
