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

  private static final int ZIP_CODE_LENGTH = 5;

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
    if (parsedRange.length != 2) {
      logger.error("zip code range should contain 2 params - lover and upper bound, param = {}",
          range);
      throw new IllegalArgumentException(
          "zip code range should contain 2 params - lover and upper bound "
      );
    }

    if (StringUtils.isEmpty(parsedRange[0]) || StringUtils.isEmpty(parsedRange[1])
        || parsedRange[0].length() != ZIP_CODE_LENGTH
        || parsedRange[1].length() != ZIP_CODE_LENGTH) {
      logger.error("zip code should have " + ZIP_CODE_LENGTH + " digits length param = {} ", range);
      throw new IllegalArgumentException(
          "zip code should have " + ZIP_CODE_LENGTH + " digits length"
      );
    }
    return parsedRange;
  }

  private String clearBrackets(String range) {
    if (!range.contains("[") || !range.contains("]")) {
      throw new IllegalArgumentException(
          "zip code should have format [00000,00000]"
      );
    }
    return range.replaceAll("\\[|\\]", "");
  }

  private String[] parseRange(String range) {
    String regexp = "(\\d{5}),(\\d{5})";
    if (!range.matches(regexp)) {
      throw new IllegalArgumentException(
          "zip code should have format [00000,00000]"
      );
    }

    return range.split(",");
  }

}
