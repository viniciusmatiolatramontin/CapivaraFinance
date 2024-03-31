package com.capivarafinance.CapivaraFinance.converter;

import com.capivarafinance.CapivaraFinance.enums.EntryType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.GenericConverter;

public class StringToEnumConverter implements Converter<String, EntryType> {

    @Override
    public EntryType convert(String source) {
        try {
            return EntryType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
}
