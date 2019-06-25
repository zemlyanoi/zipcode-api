package com.zipcodeservice.services;

import com.zipcodeservice.dto.ZipCode;
import java.util.List;
import java.util.Set;

public interface ZipCodeMerger {

  /**
   * Method is merging zip codes and remove redundant ranges
   * @param zipCodes
   * @return merged zip codes
   */
  Set<ZipCode> mergeZipCodes(List<ZipCode> zipCodes);

}
