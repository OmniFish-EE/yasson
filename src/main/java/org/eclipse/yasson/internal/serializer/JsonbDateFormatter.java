/*******************************************************************************
 * Copyright (c) 2016, 2017 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 * Roman Grigoriadi
 ******************************************************************************/

package org.eclipse.yasson.internal.serializer;

import javax.json.bind.annotation.JsonbDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;

/**
 * Formatter wrapper for different types of dates.
 *
 * @author Roman Grigoriadi
 */
public class JsonbDateFormatter {

    private static final JsonbDateFormatter DEFAULT = new JsonbDateFormatter(JsonbDateFormat.DEFAULT_FORMAT, Locale.getDefault().toLanguageTag());

    public static final String ISO_8601_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String ISO_8601_DATE_FORMAT = "yyyy-MM-dd";

    public static final String IJSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'X";

    public static final DateTimeFormatter IJSON_DATE_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(DateTimeFormatter.ISO_LOCAL_DATE)
            .appendLiteral('T')
            .appendValue(HOUR_OF_DAY, 2)
            .appendLiteral(':')
            .appendValue(MINUTE_OF_HOUR, 2)
            .optionalStart()
            .appendLiteral(':')
            .appendValue(SECOND_OF_MINUTE, 2)
            .appendLiteral('Z')
            .appendOffsetId()
            .toFormatter();

    //Java 8 date formatter is thread safe, cache it if possible
    private final DateTimeFormatter dateTimeFormatter;

    private final String format;

    private final String locale;

    /**
     * Creates an instance with cached {@link DateTimeFormatter}, format and locale.
     *
     * @param dateTimeFormatter Reused time formatter.
     * @param format Format in string.
     * @param locale Locale in string.
     */
    public JsonbDateFormatter(DateTimeFormatter dateTimeFormatter, String format, String locale) {
        this.dateTimeFormatter = dateTimeFormatter;
        this.format = format;
        this.locale = locale;
    }

    /**
     * Creates an instance with format string and locale.
     * Formatter will be created on every formatting / parsing operation.
     *
     * @param format Formatter format.
     * @param locale Locale in string.
     */
    public JsonbDateFormatter(String format, String locale) {
        this.format = format;
        this.locale = locale;
        this.dateTimeFormatter = null;
    }

    /**
     * Creates an instance with cached instance of {@link DateTimeFormatter}.
     *
     * @return Formatter instance.
     */
    public DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    /**
     * Format string to be used either by formatter.
     * Needed for formatting {@link java.util.Date} with {@link java.text.SimpleDateFormat},
     * which is not threadsafe.
     *
     * @return Format.
     */
    public String getFormat() {
        return format;
    }

    /**
     * Locale to use with formatter.
     *
     * @return Locale.
     */
    public String getLocale() {
        return locale;
    }

    public static JsonbDateFormatter getDefault() {
        return DEFAULT;
    }
}
