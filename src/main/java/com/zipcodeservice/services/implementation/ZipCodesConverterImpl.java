package com.zipcodeservice.services.implementation;

import com.zipcodeservice.dto.ZipCode;
import com.zipcodeservice.services.ZipCodesConverter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ZipCodesConverterImpl implements ZipCodesConverter {

  Logger logger = LoggerFactory.getLogger(ZipCodesConverter.class);

  @Override
  public List<ZipCode> convert(String zipcodeRanges) {
    List<ZipCode> zipCodes = new LinkedList<>();
    if (StringUtils.isEmpty(zipcodeRanges)) {
      return zipCodes;
    }

    Arrays.asList(zipcodeRanges.split(" ")).stream().forEach(e -> zipCodes.add(from(e)));
    zipCodes.sort(Comparator.comparingInt(ZipCode::getLoverBound));
    return zipCodes;
  }

  private ZipCode from(String range) {
    String[] parsedRange = splitRange(range);
    return new ZipCode(parsedRange[0], parsedRange[1]);
  }

  private String[] splitRange(String range) {
    String rangeWithoutBrackets = clearBrackets(range);
    String[] parsedRange = parseRange(rangeWithoutBrackets);
    return parsedRange;
  }

  private String clearBrackets(String range) {
    if (!range.contains("[") || !range.contains("]")) {
      throw new IllegalArgumentException(
          "zip code should have format [xxxxx,xxxxx]"
      );
    }
    return range.replaceAll("\\[|\\]", "");
  }

  private String[] parseRange(String range) {
    String regexp = "(\\d{5}),(\\d{5})";
    if (!range.matches(regexp)) {
      logger.error("bad format {}", range);
      throw new IllegalArgumentException(
          "zip code should have format [xxxxx,xxxxx]"
      );
    }

    return range.split(",");
  }

}
