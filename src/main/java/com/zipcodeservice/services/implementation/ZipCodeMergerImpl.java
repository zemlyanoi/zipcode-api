package com.zipcodeservice.services.implementation;

import com.zipcodeservice.dto.ZipCode;
import com.zipcodeservice.services.ZipCodeMerger;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ZipCodeMergerImpl implements ZipCodeMerger {

  @Override
  public Set<ZipCode> mergeZipCodes(List<ZipCode> zipCodes) {
    Set<ZipCode> mergedZipCodes = new TreeSet<>();
    if (CollectionUtils.isEmpty(zipCodes)) {
      return mergedZipCodes;
    }

    ZipCode zipCodeBuffer = null;
    for (ZipCode zipCodeItem : zipCodes) {
      if (zipCodeBuffer == null) {
        zipCodeBuffer = zipCodeItem;
      }
      if (zipCodeBuffer.getUpperBound() >= zipCodeItem.getLoverBound()) {
        zipCodeBuffer
            .setUpperBound(Math.max(zipCodeBuffer.getUpperBound(), zipCodeItem.getUpperBound()));
      } else {
        mergedZipCodes.add(zipCodeBuffer);
        zipCodeBuffer = zipCodeItem;
      }
    }
    mergedZipCodes.add(zipCodeBuffer);

    System.out.println(mergedZipCodes);
    return mergedZipCodes;
  }

}
