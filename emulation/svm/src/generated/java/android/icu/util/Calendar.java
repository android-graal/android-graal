package android.icu.util;

/**
 * <strong>[icu enhancement]</strong> ICU's replacement for {@link java.util.Calendar}.&nbsp;Methods, fields, and other functionality specific to ICU are labeled '<strong>[icu]</strong>'.
 *
 * <p><code>Calendar</code> is an abstract base class for converting between
 * a <code>Date</code> object and a set of integer fields such as
 * <code>YEAR</code>, <code>MONTH</code>, <code>DAY</code>, <code>HOUR</code>,
 * and so on. (A <code>Date</code> object represents a specific instant in
 * time with millisecond precision. See
 * {@link Date}
 * for information about the <code>Date</code> class.)
 *
 * <p>Subclasses of <code>Calendar</code> interpret a <code>Date</code>
 * according to the rules of a specific calendar system.  ICU4J contains
 * several subclasses implementing different international calendar systems.
 *
 * <p>
 * Like other locale-sensitive classes, <code>Calendar</code> provides a
 * class method, <code>getInstance</code>, for getting a generally useful
 * object of this type. <code>Calendar</code>'s <code>getInstance</code> method
 * returns a calendar of a type appropriate to the locale, whose
 * time fields have been initialized with the current date and time:
 * <blockquote>
 * <pre>Calendar rightNow = Calendar.getInstance()</pre>
 * </blockquote>
 *
 * <p>When a <code>ULocale</code> is used by <code>getInstance</code>, its
 * '<code>calendar</code>' tag and value are retrieved if present.  If a recognized
 * value is supplied, a calendar is provided and configured as appropriate.
 * Currently recognized tags are "buddhist", "chinese", "coptic", "ethiopic",
 * "gregorian", "hebrew", "islamic", "islamic-civil", "japanese", and "roc".  For
 * example: <blockquote>
 * <pre>Calendar cal = Calendar.getInstance(new ULocale("en_US@calendar=japanese"));</pre>
 * </blockquote> will return an instance of JapaneseCalendar (using en_US conventions for
 * minimum days in first week, start day of week, et cetera).
 *
 * <p>A <code>Calendar</code> object can produce all the time field values
 * needed to implement the date-time formatting for a particular language and
 * calendar style (for example, Japanese-Gregorian, Japanese-Traditional).
 * <code>Calendar</code> defines the range of values returned by certain fields,
 * as well as their meaning.  For example, the first month of the year has value
 * <code>MONTH</code> == <code>JANUARY</code> for all calendars.  Other values
 * are defined by the concrete subclass, such as <code>ERA</code> and
 * <code>YEAR</code>.  See individual field documentation and subclass
 * documentation for details.
 *
 * <p>When a <code>Calendar</code> is <em>lenient</em>, it accepts a wider range
 * of field values than it produces.  For example, a lenient
 * <code>GregorianCalendar</code> interprets <code>MONTH</code> ==
 * <code>JANUARY</code>, <code>DAY_OF_MONTH</code> == 32 as February 1.  A
 * non-lenient <code>GregorianCalendar</code> throws an exception when given
 * out-of-range field settings.  When calendars recompute field values for
 * return by <code>get()</code>, they normalize them.  For example, a
 * <code>GregorianCalendar</code> always produces <code>DAY_OF_MONTH</code>
 * values between 1 and the length of the month.
 *
 * <p><code>Calendar</code> defines a locale-specific seven day week using two
 * parameters: the first day of the week and the minimal days in first week
 * (from 1 to 7).  These numbers are taken from the locale resource data when a
 * <code>Calendar</code> is constructed.  They may also be specified explicitly
 * through the API.
 *
 * <p>When setting or getting the <code>WEEK_OF_MONTH</code> or
 * <code>WEEK_OF_YEAR</code> fields, <code>Calendar</code> must determine the
 * first week of the month or year as a reference point.  The first week of a
 * month or year is defined as the earliest seven day period beginning on
 * <code>getFirstDayOfWeek()</code> and containing at least
 * <code>getMinimalDaysInFirstWeek()</code> days of that month or year.  Weeks
 * numbered ..., -1, 0 precede the first week; weeks numbered 2, 3,... follow
 * it.  Note that the normalized numbering returned by <code>get()</code> may be
 * different.  For example, a specific <code>Calendar</code> subclass may
 * designate the week before week 1 of a year as week <em>n</em> of the previous
 * year.
 *
 * <p> When computing a <code>Date</code> from time fields, some special
 * circumstances may arise: there may be insufficient information to compute the
 * <code>Date</code> (such as only year and month but no day in the month),
 * there may be inconsistent information (such as "Tuesday, July 15, 1996" --
 * July 15, 1996 is actually a Monday), or the input time might be ambiguous
 * because of time zone transition.
 *
 * <p><strong>Insufficient information.</strong> The calendar will use default
 * information to specify the missing fields. This may vary by calendar; for
 * the Gregorian calendar, the default for a field is the same as that of the
 * start of the epoch: i.e., YEAR = 1970, MONTH = JANUARY, DATE = 1, etc.
 *
 * <p><strong>Inconsistent information.</strong> If fields conflict, the calendar
 * will give preference to fields set more recently. For example, when
 * determining the day, the calendar will look for one of the following
 * combinations of fields.  The most recent combination, as determined by the
 * most recently set single field, will be used.
 *
 * <blockquote>
 * <pre>
 * MONTH + DAY_OF_MONTH
 * MONTH + WEEK_OF_MONTH + DAY_OF_WEEK
 * MONTH + DAY_OF_WEEK_IN_MONTH + DAY_OF_WEEK
 * DAY_OF_YEAR
 * DAY_OF_WEEK + WEEK_OF_YEAR</pre>
 * </blockquote>
 *
 * For the time of day:
 *
 * <blockquote>
 * <pre>
 * HOUR_OF_DAY
 * AM_PM + HOUR</pre>
 * </blockquote>
 *
 * <p><strong>Ambiguous Wall Clock Time.</strong> When time offset from UTC has
 * changed, it produces an ambiguous time slot around the transition. For example,
 * many US locations observe daylight saving time. On the date switching to daylight
 * saving time in US, wall clock time jumps from 12:59 AM (standard) to 2:00 AM
 * (daylight). Therefore, wall clock time from 1:00 AM to 1:59 AM do not exist on
 * the date. When the input wall time fall into this missing time slot, the ICU
 * Calendar resolves the time using the UTC offset before the transition by default.
 * In this example, 1:30 AM is interpreted as 1:30 AM standard time (non-exist),
 * so the final result will be 2:30 AM daylight time.
 *
 * <p>On the date switching back to standard time, wall clock time is moved back one
 * hour at 2:00 AM. So wall clock time from 1:00 AM to 1:59 AM occur twice. In this
 * case, the ICU Calendar resolves the time using the UTC offset after the transition
 * by default. For example, 1:30 AM on the date is resolved as 1:30 AM standard time.
 *
 * <p>Ambiguous wall clock time resolution behaviors can be customized by Calendar APIs
 * {@link #setRepeatedWallTimeOption(int)} and {@link #setSkippedWallTimeOption(int)}.
 * These methods are available in ICU 49 or later versions.
 *
 * <p><strong>Note:</strong> for some non-Gregorian calendars, different
 * fields may be necessary for complete disambiguation. For example, a full
 * specification of the historial Arabic astronomical calendar requires year,
 * month, day-of-month <em>and</em> day-of-week in some cases.
 *
 * <p><strong>Note:</strong> There are certain possible ambiguities in
 * interpretation of certain singular times, which are resolved in the
 * following ways:
 * <ol>
 *     <li> 24:00:00 "belongs" to the following day. That is,
 *          23:59 on Dec 31, 1969 &lt; 24:00 on Jan 1, 1970 &lt; 24:01:00 on Jan 1, 1970
 *
 *     <li> Although historically not precise, midnight also belongs to "am",
 *          and noon belongs to "pm", so on the same day,
 *          12:00 am (midnight) &lt; 12:01 am, and 12:00 pm (noon) &lt; 12:01 pm
 * </ol>
 *
 * <p>The date or time format strings are not part of the definition of a
 * calendar, as those must be modifiable or overridable by the user at
 * runtime. Use {@link DateFormat}
 * to format dates.
 *
 * <p><strong>Field manipulation methods</strong></p>
 *
 * <p><code>Calendar</code> fields can be changed using three methods:
 * <code>set()</code>, <code>add()</code>, and <code>roll()</code>.</p>
 *
 * <p><strong><code>set(f, value)</code></strong> changes field
 * <code>f</code> to <code>value</code>.  In addition, it sets an
 * internal member variable to indicate that field <code>f</code> has
 * been changed. Although field <code>f</code> is changed immediately,
 * the calendar's milliseconds is not recomputed until the next call to
 * <code>get()</code>, <code>getTime()</code>, or
 * <code>getTimeInMillis()</code> is made. Thus, multiple calls to
 * <code>set()</code> do not trigger multiple, unnecessary
 * computations. As a result of changing a field using
 * <code>set()</code>, other fields may also change, depending on the
 * field, the field value, and the calendar system. In addition,
 * <code>get(f)</code> will not necessarily return <code>value</code>
 * after the fields have been recomputed. The specifics are determined by
 * the concrete calendar class.</p>
 *
 * <p><em>Example</em>: Consider a <code>GregorianCalendar</code>
 * originally set to August 31, 1999. Calling <code>set(Calendar.MONTH,
 * Calendar.SEPTEMBER)</code> sets the calendar to September 31,
 * 1999. This is a temporary internal representation that resolves to
 * October 1, 1999 if <code>getTime()</code>is then called. However, a
 * call to <code>set(Calendar.DAY_OF_MONTH, 30)</code> before the call to
 * <code>getTime()</code> sets the calendar to September 30, 1999, since
 * no recomputation occurs after <code>set()</code> itself.</p>
 *
 * <p><strong><code>add(f, delta)</code></strong> adds <code>delta</code>
 * to field <code>f</code>.  This is equivalent to calling <code>set(f,
 * get(f) + delta)</code> with two adjustments:</p>
 *
 * <blockquote>
 *   <p><strong>Add rule 1</strong>. The value of field <code>f</code>
 *   after the call minus the value of field <code>f</code> before the
 *   call is <code>delta</code>, modulo any overflow that has occurred in
 *   field <code>f</code>. Overflow occurs when a field value exceeds its
 *   range and, as a result, the next larger field is incremented or
 *   decremented and the field value is adjusted back into its range.</p>
 *
 *   <p><strong>Add rule 2</strong>. If a smaller field is expected to be
 *   invariant, but &nbsp; it is impossible for it to be equal to its
 *   prior value because of changes in its minimum or maximum after field
 *   <code>f</code> is changed, then its value is adjusted to be as close
 *   as possible to its expected value. A smaller field represents a
 *   smaller unit of time. <code>HOUR</code> is a smaller field than
 *   <code>DAY_OF_MONTH</code>. No adjustment is made to smaller fields
 *   that are not expected to be invariant. The calendar system
 *   determines what fields are expected to be invariant.</p>
 * </blockquote>
 *
 * <p>In addition, unlike <code>set()</code>, <code>add()</code> forces
 * an immediate recomputation of the calendar's milliseconds and all
 * fields.</p>
 *
 * <p><em>Example</em>: Consider a <code>GregorianCalendar</code>
 * originally set to August 31, 1999. Calling <code>add(Calendar.MONTH,
 * 13)</code> sets the calendar to September 30, 2000. <strong>Add rule
 * 1</strong> sets the <code>MONTH</code> field to September, since
 * adding 13 months to August gives September of the next year. Since
 * <code>DAY_OF_MONTH</code> cannot be 31 in September in a
 * <code>GregorianCalendar</code>, <strong>add rule 2</strong> sets the
 * <code>DAY_OF_MONTH</code> to 30, the closest possible value. Although
 * it is a smaller field, <code>DAY_OF_WEEK</code> is not adjusted by
 * rule 2, since it is expected to change when the month changes in a
 * <code>GregorianCalendar</code>.</p>
 *
 * <p><strong><code>roll(f, delta)</code></strong> adds
 * <code>delta</code> to field <code>f</code> without changing larger
 * fields. This is equivalent to calling <code>add(f, delta)</code> with
 * the following adjustment:</p>
 *
 * <blockquote>
 *   <p><strong>Roll rule</strong>. Larger fields are unchanged after the
 *   call. A larger field represents a larger unit of
 *   time. <code>DAY_OF_MONTH</code> is a larger field than
 *   <code>HOUR</code>.</p>
 * </blockquote>
 *
 * <p><em>Example</em>: Consider a <code>GregorianCalendar</code>
 * originally set to August 31, 1999. Calling <code>roll(Calendar.MONTH,
 * 8)</code> sets the calendar to April 30, <strong>1999</strong>.  Add
 * rule 1 sets the <code>MONTH</code> field to April. Using a
 * <code>GregorianCalendar</code>, the <code>DAY_OF_MONTH</code> cannot
 * be 31 in the month April. Add rule 2 sets it to the closest possible
 * value, 30. Finally, the <strong>roll rule</strong> maintains the
 * <code>YEAR</code> field value of 1999.</p>
 *
 * <p><em>Example</em>: Consider a <code>GregorianCalendar</code>
 * originally set to Sunday June 6, 1999. Calling
 * <code>roll(Calendar.WEEK_OF_MONTH, -1)</code> sets the calendar to
 * Tuesday June 1, 1999, whereas calling
 * <code>add(Calendar.WEEK_OF_MONTH, -1)</code> sets the calendar to
 * Sunday May 30, 1999. This is because the roll rule imposes an
 * additional constraint: The <code>MONTH</code> must not change when the
 * <code>WEEK_OF_MONTH</code> is rolled. Taken together with add rule 1,
 * the resultant date must be between Tuesday June 1 and Saturday June
 * 5. According to add rule 2, the <code>DAY_OF_WEEK</code>, an invariant
 * when changing the <code>WEEK_OF_MONTH</code>, is set to Tuesday, the
 * closest possible value to Sunday (where Sunday is the first day of the
 * week).</p>
 *
 * <p><strong>Usage model</strong>. To motivate the behavior of
 * <code>add()</code> and <code>roll()</code>, consider a user interface
 * component with increment and decrement buttons for the month, day, and
 * year, and an underlying <code>GregorianCalendar</code>. If the
 * interface reads January 31, 1999 and the user presses the month
 * increment button, what should it read? If the underlying
 * implementation uses <code>set()</code>, it might read March 3, 1999. A
 * better result would be February 28, 1999. Furthermore, if the user
 * presses the month increment button again, it should read March 31,
 * 1999, not March 28, 1999. By saving the original date and using either
 * <code>add()</code> or <code>roll()</code>, depending on whether larger
 * fields should be affected, the user interface can behave as most users
 * will intuitively expect.</p>
 *
 * <p><b>Note:</b> You should always use {@link #roll roll} and {@link #add add} rather
 * than attempting to perform arithmetic operations directly on the fields
 * of a <tt>Calendar</tt>.  It is quite possible for <tt>Calendar</tt> subclasses
 * to have fields with non-linear behavior, for example missing months
 * or days during non-leap years.  The subclasses' <tt>add</tt> and <tt>roll</tt>
 * methods will take this into account, while simple arithmetic manipulations
 * may give invalid results.
 *
 * <p><big><big><b>Calendar Architecture in ICU4J</b></big></big></p>
 *
 * <p>Recently the implementation of <code>Calendar</code> has changed
 * significantly in order to better support subclassing. The original
 * <code>Calendar</code> class was designed to support subclassing, but
 * it had only one implemented subclass, <code>GregorianCalendar</code>.
 * With the implementation of several new calendar subclasses, including
 * the <code>BuddhistCalendar</code>, <code>ChineseCalendar</code>,
 * <code>HebrewCalendar</code>, <code>IslamicCalendar</code>, and
 * <code>JapaneseCalendar</code>, the subclassing API has been reworked
 * thoroughly. This section details the new subclassing API and other
 * ways in which <code>android.icu.util.Calendar</code> differs from
 * <code>java.util.Calendar</code>.
 * </p>
 *
 * <p><big><b>Changes</b></big></p>
 *
 * <p>Overview of changes between the classic <code>Calendar</code>
 * architecture and the new architecture.
 *
 * <ul>
 *
 *   <li>The <code>fields[]</code> array is <code>private</code> now
 *     instead of <code>protected</code>.  Subclasses must access it
 *     using the methods {@link #internalSet} and
 *     {@link #internalGet}.  <b>Motivation:</b> Subclasses should
 *     not directly access data members.</li>
 *
 *   <li>The <code>time</code> long word is <code>private</code> now
 *     instead of <code>protected</code>.  Subclasses may access it using
 *     the method {@link #internalGetTimeInMillis}, which does not
 *     provoke an update. <b>Motivation:</b> Subclasses should not
 *     directly access data members.</li>
 *
 *   <li>The scope of responsibility of subclasses has been drastically
 *     reduced. As much functionality as possible is implemented in the
 *     <code>Calendar</code> base class. As a result, it is much easier
 *     to subclass <code>Calendar</code>. <b>Motivation:</b> Subclasses
 *     should not have to reimplement common code. Certain behaviors are
 *     common across calendar systems: The definition and behavior of
 *     week-related fields and time fields, the arithmetic
 *     ({@link #add(int, int) add} and {@link #roll(int, int) roll}) behavior of many
 *     fields, and the field validation system.</li>
 *
 *   <li>The subclassing API has been completely redesigned.</li>
 *
 *   <li>The <code>Calendar</code> base class contains some Gregorian
 *     calendar algorithmic support that subclasses can use (specifically
 *     in {@link #handleComputeFields}).  Subclasses can use the
 *     methods <code>getGregorianXxx()</code> to obtain precomputed
 *     values. <b>Motivation:</b> This is required by all
 *     <code>Calendar</code> subclasses in order to implement consistent
 *     time zone behavior, and Gregorian-derived systems can use the
 *     already computed data.</li>
 *
 *   <li>The <code>FIELD_COUNT</code> constant has been removed. Use
 *     {@link #getFieldCount}.  In addition, framework API has been
 *     added to allow subclasses to define additional fields.
 *     <b>Motivation: </b>The number of fields is not constant across
 *     calendar systems.</li>
 *
 *   <li>The range of handled dates has been narrowed from +/-
 *     ~300,000,000 years to +/- ~5,000,000 years. In practical terms
 *     this should not affect clients. However, it does mean that client
 *     code cannot be guaranteed well-behaved results with dates such as
 *     <code>Date(Long.MIN_VALUE)</code> or
 *     <code>Date(Long.MAX_VALUE)</code>. Instead, the
 *     <code>Calendar</code> protected constants should be used.
 *     <b>Motivation:</b> With
 *     the addition of the {@link #JULIAN_DAY} field, Julian day
 *     numbers must be restricted to a 32-bit <code>int</code>.  This
 *     restricts the overall supported range. Furthermore, restricting
 *     the supported range simplifies the computations by removing
 *     special case code that was used to accommodate arithmetic overflow
 *     at millis near <code>Long.MIN_VALUE</code> and
 *     <code>Long.MAX_VALUE</code>.</li>
 *
 *   <li>New fields are implemented: {@link #JULIAN_DAY} defines
 *     single-field specification of the
 *     date. {@link #MILLISECONDS_IN_DAY} defines a single-field
 *     specification of the wall time. {@link #DOW_LOCAL} and
 *     {@link #YEAR_WOY} implement localized day-of-week and
 *     week-of-year behavior.</li>
 *
 *   <li>Subclasses can access protected millisecond constants
 *   defined in <code>Calendar</code>.</li>
 *
 *   <li>New API has been added to support calendar-specific subclasses
 *     of <code>DateFormat</code>.</li>
 *
 *   <li>Several subclasses have been implemented, representing
 *     various international calendar systems.</li>
 *
 * </ul>
 *
 * <p><big><b>Subclass API</b></big></p>
 *
 * <p>The original <code>Calendar</code> API was based on the experience
 * of implementing a only a single subclass,
 * <code>GregorianCalendar</code>. As a result, all of the subclassing
 * kinks had not been worked out. The new subclassing API has been
 * refined based on several implemented subclasses. This includes methods
 * that must be overridden and methods for subclasses to call. Subclasses
 * no longer have direct access to <code>fields</code> and
 * <code>stamp</code>. Instead, they have new API to access
 * these. Subclasses are able to allocate the <code>fields</code> array
 * through a protected framework method; this allows subclasses to
 * specify additional fields. </p>
 *
 * <p>More functionality has been moved into the base class. The base
 * class now contains much of the computational machinery to support the
 * Gregorian calendar. This is based on two things: (1) Many calendars
 * are based on the Gregorian calendar (such as the Buddhist and Japanese
 * imperial calendars). (2) <em>All</em> calendars require basic
 * Gregorian support in order to handle timezone computations. </p>
 *
 * <p>Common computations have been moved into
 * <code>Calendar</code>. Subclasses no longer compute the week related
 * fields and the time related fields. These are commonly handled for all
 * calendars by the base class. </p>
 *
 * <p><b>Subclass computation of time <tt>=&gt;</tt> fields</b>
 *
 * <p>The {@link #ERA}, {@link #YEAR},
 * {@link #EXTENDED_YEAR}, {@link #MONTH},
 * {@link #DAY_OF_MONTH}, and {@link #DAY_OF_YEAR} fields are
 * computed by the subclass, based on the Julian day. All other fields
 * are computed by <code>Calendar</code>.
 *
 * <ul>
 *
 *   <li>Subclasses should implement {@link #handleComputeFields}
 *     to compute the {@link #ERA}, {@link #YEAR},
 *     {@link #EXTENDED_YEAR}, {@link #MONTH},
 *     {@link #DAY_OF_MONTH}, and {@link #DAY_OF_YEAR} fields,
 *     based on the value of the {@link #JULIAN_DAY} field. If there
 *     are calendar-specific fields not defined by <code>Calendar</code>,
 *     they must also be computed. These are the only fields that the
 *     subclass should compute. All other fields are computed by the base
 *     class, so time and week fields behave in a consistent way across
 *     all calendars. The default version of this method in
 *     <code>Calendar</code> implements a proleptic Gregorian
 *     calendar. Within this method, subclasses may call
 *     <code>getGregorianXxx()</code> to obtain the Gregorian calendar
 *     month, day of month, and extended year for the given date.</li>
 *
 * </ul>
 *
 * <p><b>Subclass computation of fields <tt>=&gt;</tt> time</b>
 *
 * <p>The interpretation of most field values is handled entirely by
 * <code>Calendar</code>. <code>Calendar</code> determines which fields
 * are set, which are not, which are set more recently, and so on. In
 * addition, <code>Calendar</code> handles the computation of the time
 * from the time fields and handles the week-related fields. The only
 * thing the subclass must do is determine the extended year, based on
 * the year fields, and then, given an extended year and a month, it must
 * return a Julian day number.
 *
 * <ul>
 *
 *   <li>Subclasses should implement {@link #handleGetExtendedYear}
 *     to return the extended year for this calendar system, based on the
 *     {@link #YEAR}, {@link #EXTENDED_YEAR}, and any fields that
 *     the calendar system uses that are larger than a year, such as
 *     {@link #ERA}.</li>
 *
 *   <li>Subclasses should implement {@link #handleComputeMonthStart}
 *     to return the Julian day number
 *     associated with a month and extended year. This is the Julian day
 *     number of the day before the first day of the month. The month
 *     number is zero-based. This computation should not depend on any
 *     field values.</li>
 *
 * </ul>
 *
 * <p><b>Other methods</b>
 *
 * <ul>
 *
 *   <li>Subclasses should implement {@link #handleGetMonthLength}
 *     to return the number of days in a
 *     given month of a given extended year. The month number, as always,
 *     is zero-based.</li>
 *
 *   <li>Subclasses should implement {@link #handleGetYearLength}
 *     to return the number of days in the given
 *     extended year. This method is used by
 *     <tt>computeWeekFields</tt> to compute the
 *     {@link #WEEK_OF_YEAR} and {@link #YEAR_WOY} fields.</li>
 *
 *   <li>Subclasses should implement {@link #handleGetLimit}
 *     to return the protected values of a field, depending on the value of
 *     <code>limitType</code>. This method only needs to handle the
 *     fields {@link #ERA}, {@link #YEAR}, {@link #MONTH},
 *     {@link #WEEK_OF_YEAR}, {@link #WEEK_OF_MONTH},
 *     {@link #DAY_OF_MONTH}, {@link #DAY_OF_YEAR},
 *     {@link #DAY_OF_WEEK_IN_MONTH}, {@link #YEAR_WOY}, and
 *     {@link #EXTENDED_YEAR}.  Other fields are invariant (with
 *     respect to calendar system) and are handled by the base
 *     class.</li>
 *
 *   <li>Optionally, subclasses may override {@link #validateField}
 *     to check any subclass-specific fields. If the
 *     field's value is out of range, the method should throw an
 *     <code>IllegalArgumentException</code>. The method may call
 *     <code>super.validateField(field)</code> to handle fields in a
 *     generic way, that is, to compare them to the range
 *     <code>getMinimum(field)</code>..<code>getMaximum(field)</code>.</li>
 *
 *   <li>Optionally, subclasses may override
 *     {@link #handleCreateFields} to create an <code>int[]</code>
 *     array large enough to hold the calendar's fields. This is only
 *     necessary if the calendar defines additional fields beyond those
 *     defined by <code>Calendar</code>. The length of the result must be
 *     be between the base and maximum field counts.</li>
 *
 *   <li>Optionally, subclasses may override
 *     {@link #handleGetDateFormat} to create a
 *     <code>DateFormat</code> appropriate to this calendar. This is only
 *     required if a calendar subclass redefines the use of a field (for
 *     example, changes the {@link #ERA} field from a symbolic field
 *     to a numeric one) or defines an additional field.</li>
 *
 *   <li>Optionally, subclasses may override {@link #roll roll} and
 *     {@link #add add} to handle fields that are discontinuous. For
 *     example, in the Hebrew calendar the month &quot;Adar I&quot; only
 *     occurs in leap years; in other years the calendar jumps from
 *     Shevat (month #4) to Adar (month #6). The {@link
 *     HebrewCalendar#add HebrewCalendar.add} and {@link
 *     HebrewCalendar#roll HebrewCalendar.roll} methods take this into
 *     account, so that adding 1 month to Shevat gives the proper result
 *     (Adar) in a non-leap year. The protected utility method {@link
 *     #pinField pinField} is often useful when implementing these two
 *     methods. </li>
 *
 * </ul>
 *
 * <p><big><b>Normalized behavior</b></big>
 *
 * <p>The behavior of certain fields has been made consistent across all
 * calendar systems and implemented in <code>Calendar</code>.
 *
 * <ul>
 *
 *   <li>Time is normalized. Even though some calendar systems transition
 *     between days at sunset or at other times, all ICU4J calendars
 *     transition between days at <em>local zone midnight</em>.  This
 *     allows ICU4J to centralize the time computations in
 *     <code>Calendar</code> and to maintain basic correspondences
 *     between calendar systems. Affected fields: {@link #AM_PM},
 *     {@link #HOUR}, {@link #HOUR_OF_DAY}, {@link #MINUTE},
 *     {@link #SECOND}, {@link #MILLISECOND},
 *     {@link #ZONE_OFFSET}, and {@link #DST_OFFSET}.</li>
 *
 *   <li>DST behavior is normalized. Daylight savings time behavior is
 *     computed the same for all calendar systems, and depends on the
 *     value of several <code>GregorianCalendar</code> fields: the
 *     {@link #YEAR}, {@link #MONTH}, and
 *     {@link #DAY_OF_MONTH}. As a result, <code>Calendar</code>
 *     always computes these fields, even for non-Gregorian calendar
 *     systems. These fields are available to subclasses.</li>
 *
 *   <li>Weeks are normalized. Although locales define the week
 *     differently, in terms of the day on which it starts, and the
 *     designation of week number one of a month or year, they all use a
 *     common mechanism. Furthermore, the day of the week has a simple
 *     and consistent definition throughout history. For example,
 *     although the Gregorian calendar introduced a discontinuity when
 *     first instituted, the day of week was not disrupted. For this
 *     reason, the fields {@link #DAY_OF_WEEK}, <code>WEEK_OF_YEAR,
 *     WEEK_OF_MONTH</code>, {@link #DAY_OF_WEEK_IN_MONTH},
 *     {@link #DOW_LOCAL}, {@link #YEAR_WOY} are all computed in
 *     a consistent way in the base class, based on the
 *     {@link #EXTENDED_YEAR}, {@link #DAY_OF_YEAR},
 *     {@link #MONTH}, and {@link #DAY_OF_MONTH}, which are
 *     computed by the subclass.</li>
 *
 * </ul>
 *
 * <p><big><b>Supported range</b></big>
 *
 * <p>The allowable range of <code>Calendar</code> has been
 * narrowed. <code>GregorianCalendar</code> used to attempt to support
 * the range of dates with millisecond values from
 * <code>Long.MIN_VALUE</code> to <code>Long.MAX_VALUE</code>. This
 * introduced awkward constructions (hacks) which slowed down
 * performance. It also introduced non-uniform behavior at the
 * boundaries. The new <code>Calendar</code> protocol specifies the
 * maximum range of supportable dates as those having Julian day numbers
 * of <code>-0x7F000000</code> to <code>+0x7F000000</code>. This
 * corresponds to years from ~5,800,000 BCE to ~5,800,000 CE. Programmers
 * should use the protected constants in <code>Calendar</code> to
 * specify an extremely early or extremely late date.</p>
 *
 * <p><big><b>General notes</b></big>
 *
 * <ul>
 *
 *   <li>Calendars implementations are <em>proleptic</em>. For example,
 *     even though the Gregorian calendar was not instituted until the
 *     16th century, the <code>GregorianCalendar</code> class supports
 *     dates before the historical onset of the calendar by extending the
 *     calendar system backward in time. Similarly, the
 *     <code>HebrewCalendar</code> extends backward before the start of
 *     its epoch into zero and negative years. Subclasses do not throw
 *     exceptions because a date precedes the historical start of a
 *     calendar system. Instead, they implement
 *     {@link #handleGetLimit} to return appropriate limits on
 *     {@link #YEAR}, {@link #ERA}, etc. fields. Then, if the
 *     calendar is set to not be lenient, out-of-range field values will
 *     trigger an exception.</li>
 *
 *   <li>Calendar system subclasses compute a <em>extended
 *     year</em>. This differs from the {@link #YEAR} field in that
 *     it ranges over all integer values, including zero and negative
 *     values, and it encapsulates the information of the
 *     {@link #YEAR} field and all larger fields.  Thus, for the
 *     Gregorian calendar, the {@link #EXTENDED_YEAR} is computed as
 *     <code>ERA==AD ? YEAR : 1-YEAR</code>. Another example is the Mayan
 *     long count, which has years (<code>KUN</code>) and nested cycles
 *     of years (<code>KATUN</code> and <code>BAKTUN</code>). The Mayan
 *     {@link #EXTENDED_YEAR} is computed as <code>TUN + 20 * (KATUN
 *     + 20 * BAKTUN)</code>. The <code>Calendar</code> base class uses
 *     the {@link #EXTENDED_YEAR} field to compute the week-related
 *     fields.</li>
 *
 * </ul>
 *
 * @see          Date
 * @see          GregorianCalendar
 * @see          TimeZone
 * @see          DateFormat
 * @author Mark Davis, Deborah Goldsmith, Chen-Lieh Huang, Alan Liu, Laura Werner
 */
public final class Calendar {
    /**
     * Value of the <code>AM_PM</code> field indicating the
     * period of the day from midnight to just before noon.
     */
    public static final int AM = 0;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating
     * whether the <code>HOUR</code> is before or after noon.
     * E.g., at 10:04:15.250 PM the <code>AM_PM</code> is <code>PM</code>.
     * @see #AM
     * @see #PM
     * @see #HOUR
     */
    public static final int AM_PM = 9;

    /**
     * Value of the <code>MONTH</code> field indicating the
     * fourth month of the year.
     */
    public static final int APRIL = 3;

    /**
     * Value of the <code>MONTH</code> field indicating the
     * eighth month of the year.
     */
    public static final int AUGUST = 7;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * day of the month. This is a synonym for {@link #DAY_OF_MONTH}.
     * The first day of the month has value 1.
     * @see #DAY_OF_MONTH
     */
    public static final int DATE = 5;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * day of the month. This is a synonym for {@link #DATE}.
     * The first day of the month has value 1.
     * @see #DATE
     */
    public static final int DAY_OF_MONTH = 5;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the day
     * of the week.  This field takes values {@link #SUNDAY},
     * {@link #MONDAY}, {@link #TUESDAY}, {@link #WEDNESDAY},
     * {@link #THURSDAY}, {@link #FRIDAY}, and {@link #SATURDAY}.
     * @see #SUNDAY
     * @see #MONDAY
     * @see #TUESDAY
     * @see #WEDNESDAY
     * @see #THURSDAY
     * @see #FRIDAY
     * @see #SATURDAY
     */
    public static final int DAY_OF_WEEK = 7;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * ordinal number of the day of the week within the current month. Together
     * with the {@link #DAY_OF_WEEK} field, this uniquely specifies a day
     * within a month.  Unlike {@link #WEEK_OF_MONTH} and
     * {@link #WEEK_OF_YEAR}, this field's value does <em>not</em> depend on
     * {@link #getFirstDayOfWeek()} or
     * {@link #getMinimalDaysInFirstWeek()}.  <code>DAY_OF_MONTH 1</code>
     * through <code>7</code> always correspond to <code>DAY_OF_WEEK_IN_MONTH
     * 1</code>; <code>8</code> through <code>15</code> correspond to
     * <code>DAY_OF_WEEK_IN_MONTH 2</code>, and so on.
     * <code>DAY_OF_WEEK_IN_MONTH 0</code> indicates the week before
     * <code>DAY_OF_WEEK_IN_MONTH 1</code>.  Negative values count back from the
     * end of the month, so the last Sunday of a month is specified as
     * <code>DAY_OF_WEEK = SUNDAY, DAY_OF_WEEK_IN_MONTH = -1</code>.  Because
     * negative values count backward they will usually be aligned differently
     * within the month than positive values.  For example, if a month has 31
     * days, <code>DAY_OF_WEEK_IN_MONTH -1</code> will overlap
     * <code>DAY_OF_WEEK_IN_MONTH 5</code> and the end of <code>4</code>.
     * @see #DAY_OF_WEEK
     * @see #WEEK_OF_MONTH
     */
    public static final int DAY_OF_WEEK_IN_MONTH = 8;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the day
     * number within the current year.  The first day of the year has value 1.
     */
    public static final int DAY_OF_YEAR = 6;

    /**
     * Value of the <code>MONTH</code> field indicating the
     * twelfth month of the year.
     */
    public static final int DECEMBER = 11;

    /**
     * <strong>[icu]</strong> Field number for <code>get()</code> and <code>set()</code>
     * indicating the localized day of week.  This will be a value from 1
     * to 7 inclusive, with 1 being the localized first day of the week.
     */
    public static final int DOW_LOCAL = 18;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * daylight savings offset in milliseconds.
     */
    public static final int DST_OFFSET = 16;

    /**
     * The Julian day of the epoch, that is, January 1, 1970 on the
     * Gregorian calendar.
     */
    protected static final int EPOCH_JULIAN_DAY = 2440588;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * era, e.g., AD or BC in the Julian calendar. This is a calendar-specific
     * value; see subclass documentation.
     * @see GregorianCalendar#AD
     * @see GregorianCalendar#BC
     */
    public static final int ERA = 0;

    /**
     * <strong>[icu]</strong> Field number for <code>get()</code> and <code>set()</code>
     * indicating the extended year.  This is a single number designating
     * the year of this calendar system, encompassing all supra-year
     * fields.  For example, for the Julian calendar system, year numbers
     * are positive, with an era of BCE or CE.  An extended year value for
     * the Julian calendar system assigns positive values to CE years and
     * negative values to BCE years, with 1 BCE being year 0.
     */
    public static final int EXTENDED_YEAR = 19;

    /**
     * Value of the <code>MONTH</code> field indicating the
     * second month of the year.
     */
    public static final int FEBRUARY = 1;

    /**
     * Value of the <code>DAY_OF_WEEK</code> field indicating
     * Friday.
     */
    public static final int FRIDAY = 6;

    /**
     * Limit type for <code>getLimit()</code> and <code>handleGetLimit()</code>
     * indicating the greatest minimum value that a field can take.
     * @see #getLimit
     * @see #handleGetLimit
     */
    protected static final int GREATEST_MINIMUM = 1;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * hour of the morning or afternoon. <code>HOUR</code> is used for the 12-hour
     * clock.
     * E.g., at 10:04:15.250 PM the <code>HOUR</code> is 10.
     * @see #AM_PM
     * @see #HOUR_OF_DAY
     */
    public static final int HOUR = 10;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * hour of the day. <code>HOUR_OF_DAY</code> is used for the 24-hour clock.
     * E.g., at 10:04:15.250 PM the <code>HOUR_OF_DAY</code> is 22.
     * @see #HOUR
     */
    public static final int HOUR_OF_DAY = 11;

    /**
     * Value of the time stamp <code>stamp[]</code> indicating that a field
     * has been set via computations from the time or from other fields.
     * @see #UNSET
     * @see #MINIMUM_USER_STAMP
     */
    protected static final int INTERNALLY_SET = 1;

    /**
     * <strong>[icu]</strong> Field indicating whether or not the current month is a leap month.
     * Should have a value of 0 for non-leap months, and 1 for leap months.
     */
    public static final int IS_LEAP_MONTH = 22;

    /**
     * Value of the <code>MONTH</code> field indicating the
     * first month of the year.
     */
    public static final int JANUARY = 0;

    /**
     * The Julian day of the Gregorian epoch, that is, January 1, 1 on the
     * Gregorian calendar.
     */
    protected static final int JAN_1_1_JULIAN_DAY = 1721426;

    /**
     * <strong>[icu]</strong> Field number for <code>get()</code> and <code>set()</code>
     * indicating the modified Julian day number.  This is different from
     * the conventional Julian day number in two regards.  First, it
     * demarcates days at local zone midnight, rather than noon GMT.
     * Second, it is a local number; that is, it depends on the local time
     * zone.  It can be thought of as a single number that encompasses all
     * the date-related fields.
     */
    public static final int JULIAN_DAY = 20;

    /**
     * Value of the <code>MONTH</code> field indicating the
     * seventh month of the year.
     */
    public static final int JULY = 6;

    /**
     * Value of the <code>MONTH</code> field indicating the
     * sixth month of the year.
     */
    public static final int JUNE = 5;

    /**
     * Limit type for <code>getLimit()</code> and <code>handleGetLimit()</code>
     * indicating the least maximum value that a field can take.
     * @see #getLimit
     * @see #handleGetLimit
     */
    protected static final int LEAST_MAXIMUM = 2;

    /**
     * Value of the <code>MONTH</code> field indicating the
     * third month of the year.
     */
    public static final int MARCH = 2;

    /**
     * Limit type for <code>getLimit()</code> and <code>handleGetLimit()</code>
     * indicating the maximum value that a field can take (greatest maximum).
     * @see #getLimit
     * @see #handleGetLimit
     */
    protected static final int MAXIMUM = 3;

    /**
     * The maximum number of fields possible.  Subclasses must not define
     * more total fields than this number.
     * @deprecated ICU 58 The numeric value may change over time, see ICU ticket #12420.
     */
    @Deprecated
    protected static final int MAX_FIELD_COUNT = 32;

    /**
     * The maximum supported Julian day.  This value is equivalent to
     * {@link #MAX_MILLIS} and {@link #MAX_DATE}.
     * @see #JULIAN_DAY
     */
    protected static final int MAX_JULIAN = 2130706432;

    /**
     * The maximum supported epoch milliseconds.  This value is equivalent
     * to {@link #MAX_JULIAN} and {@link #MAX_DATE}.
     */
    protected static final long MAX_MILLIS = 183882168921600000L;

    /**
     * Value of the <code>MONTH</code> field indicating the
     * fifth month of the year.
     */
    public static final int MAY = 4;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * millisecond within the second.
     * E.g., at 10:04:15.250 PM the <code>MILLISECOND</code> is 250.
     */
    public static final int MILLISECOND = 14;

    /**
     * <strong>[icu]</strong> Field number for <code>get()</code> and <code>set()</code>
     * indicating the milliseconds in the day.  This ranges from 0 to
     * 23:59:59.999 (regardless of DST).  This field behaves
     * <em>exactly</em> like a composite of all time-related fields, not
     * including the zone fields.  As such, it also reflects
     * discontinuities of those fields on DST transition days.  On a day of
     * DST onset, it will jump forward.  On a day of DST cessation, it will
     * jump backward.  This reflects the fact that is must be combined with
     * the DST_OFFSET field to obtain a unique local time value.
     */
    public static final int MILLISECONDS_IN_DAY = 21;

    /**
     * Limit type for <code>getLimit()</code> and <code>handleGetLimit()</code>
     * indicating the minimum value that a field can take (least minimum).
     * @see #getLimit
     * @see #handleGetLimit
     */
    protected static final int MINIMUM = 0;

    /**
     * If the time stamp <code>stamp[]</code> has a value greater than or
     * equal to <code>MINIMUM_USER_SET</code> then it has been set by the
     * user via a call to <code>set()</code>.
     * @see #UNSET
     * @see #INTERNALLY_SET
     */
    protected static final int MINIMUM_USER_STAMP = 2;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * minute within the hour.
     * E.g., at 10:04:15.250 PM the <code>MINUTE</code> is 4.
     */
    public static final int MINUTE = 12;

    /**
     * The minimum supported Julian day.  This value is equivalent to
     * {@link #MIN_MILLIS} and {@link #MIN_DATE}.
     * @see #JULIAN_DAY
     */
    protected static final int MIN_JULIAN = -2130706432;

    /**
     * The minimum supported epoch milliseconds.  This value is equivalent
     * to {@link #MIN_JULIAN} and {@link #MIN_DATE}.
     */
    protected static final long MIN_MILLIS = -184303902528000000L;

    /**
     * Value of the <code>DAY_OF_WEEK</code> field indicating
     * Monday.
     */
    public static final int MONDAY = 2;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * month. This is a calendar-specific value. The first month of the year is
     * <code>JANUARY</code>; the last depends on the number of months in a year.
     * @see #JANUARY
     * @see #FEBRUARY
     * @see #MARCH
     * @see #APRIL
     * @see #MAY
     * @see #JUNE
     * @see #JULY
     * @see #AUGUST
     * @see #SEPTEMBER
     * @see #OCTOBER
     * @see #NOVEMBER
     * @see #DECEMBER
     * @see #UNDECIMBER
     */
    public static final int MONTH = 2;

    /**
     * Value of the <code>MONTH</code> field indicating the
     * eleventh month of the year.
     */
    public static final int NOVEMBER = 10;

    /**
     * Value of the <code>MONTH</code> field indicating the
     * tenth month of the year.
     */
    public static final int OCTOBER = 9;

    /**
     * The number of milliseconds in one day.  Although ONE_DAY and
     * ONE_WEEK can fit into ints, they must be longs in order to prevent
     * arithmetic overflow when performing (bug 4173516).
     */
    protected static final long ONE_DAY = 86400000L;

    /**
     * The number of milliseconds in one hour.
     */
    protected static final int ONE_HOUR = 3600000;

    /**
     * The number of milliseconds in one minute.
     */
    protected static final int ONE_MINUTE = 60000;

    /**
     * The number of milliseconds in one second.
     */
    protected static final int ONE_SECOND = 1000;

    /**
     * The number of milliseconds in one week.  Although ONE_DAY and
     * ONE_WEEK can fit into ints, they must be longs in order to prevent
     * arithmetic overflow when performing (bug 4173516).
     */
    protected static final long ONE_WEEK = 604800000L;

    /**
     * <strong>[icu]</strong> Field indicating the month. This is a calendar-specific value.
     * Differ from MONTH, this value is continuous and unique within a
     * year and range from 0 to 11 or 0 to 12 depending on how many months in a
     * year, the calendar system has leap month or not, and in leap year or not.
     * It is the ordinal position of that month in the corresponding year of
     * the calendar. For Chinese, Dangi, and Hebrew calendar, the range is
     * 0 to 11 in non-leap years and 0 to 12 in leap years. For Coptic and Ethiopian
     * calendar, the range is always 0 to 12. For other calendars supported by
     * ICU now, the range is 0 to 11. When the number of months in a year of the
     * identified calendar is variable, a different ORDINAL_MONTH value can
     * be used for dates that are part of the same named month in different years.
     * For example, in the Hebrew calendar, "1 Nisan 5781" is associated with
     * ORDINAL_MONTH value 6 while "1 Nisan 5782" is associated with
     * ORDINAL_MONTH value 7 because 5782 is a leap year and Nisan follows
     * the insertion of Adar I. In Chinese calendar, "Year 4664 Month 6 Day 2"
     * is associated with ORDINAL_MONTH value 5 while "Year 4665 Month 6 Day 2"
     * is associated with ORDINAL_MONTH value 6 because 4665 is a leap year
     * and there is an extra "Leap Month 5" which associated with ORDINAL_MONTH
     * value 5 before "Month 6" of year 4664.
     */
    public static final int ORDINAL_MONTH = 23;

    /**
     * Value of the <code>AM_PM</code> field indicating the
     * period of the day from noon to just before midnight.
     */
    public static final int PM = 1;

    /**
     * Value to OR against resolve table field values for remapping.
     * @see #resolveFields
     */
    protected static final int RESOLVE_REMAP = 32;

    /**
     * Value of the <code>DAY_OF_WEEK</code> field indicating
     * Saturday.
     */
    public static final int SATURDAY = 7;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * second within the minute.
     * E.g., at 10:04:15.250 PM the <code>SECOND</code> is 15.
     */
    public static final int SECOND = 13;

    /**
     * Value of the <code>MONTH</code> field indicating the
     * ninth month of the year.
     */
    public static final int SEPTEMBER = 8;

    /**
     * Value of the <code>DAY_OF_WEEK</code> field indicating
     * Sunday.
     */
    public static final int SUNDAY = 1;

    /**
     * Value of the <code>DAY_OF_WEEK</code> field indicating
     * Thursday.
     */
    public static final int THURSDAY = 5;

    /**
     * Value of the <code>DAY_OF_WEEK</code> field indicating
     * Tuesday.
     */
    public static final int TUESDAY = 3;

    /**
     * Value of the <code>MONTH</code> field indicating the
     * thirteenth month of the year. Although {@link GregorianCalendar}
     * does not use this value, lunar calendars do.
     */
    public static final int UNDECIMBER = 12;

    /**
     * Value of the time stamp <code>stamp[]</code> indicating that
     * a field has not been set since the last call to <code>clear()</code>.
     * @see #INTERNALLY_SET
     * @see #MINIMUM_USER_STAMP
     */
    protected static final int UNSET = 0;

    /**
     * <strong>[icu]</strong>Option used by {@link #setRepeatedWallTimeOption(int)} and
     * {@link #setSkippedWallTimeOption(int)} specifying an ambiguous wall time
     * to be interpreted as the earliest.
     * @see #setRepeatedWallTimeOption(int)
     * @see #getRepeatedWallTimeOption()
     * @see #setSkippedWallTimeOption(int)
     * @see #getSkippedWallTimeOption()
     */
    public static final int WALLTIME_FIRST = 1;

    /**
     * <strong>[icu]</strong>Option used by {@link #setRepeatedWallTimeOption(int)} and
     * {@link #setSkippedWallTimeOption(int)} specifying an ambiguous wall time
     * to be interpreted as the latest.
     * @see #setRepeatedWallTimeOption(int)
     * @see #getRepeatedWallTimeOption()
     * @see #setSkippedWallTimeOption(int)
     * @see #getSkippedWallTimeOption()
     */
    public static final int WALLTIME_LAST = 0;

    /**
     * <strong>[icu]</strong>Option used by {@link #setSkippedWallTimeOption(int)} specifying an
     * ambiguous wall time to be interpreted as the next valid wall time.
     * @see #setSkippedWallTimeOption(int)
     * @see #getSkippedWallTimeOption()
     */
    public static final int WALLTIME_NEXT_VALID = 2;

    /**
     * Value of the <code>DAY_OF_WEEK</code> field indicating
     * Wednesday.
     */
    public static final int WEDNESDAY = 4;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * week number within the current month.  The first week of the month, as
     * defined by {@link #getFirstDayOfWeek()} and
     * {@link #getMinimalDaysInFirstWeek()}, has value 1.  Subclasses define
     * the value of {@link #WEEK_OF_MONTH} for days before the first week of
     * the month.
     * @see #getFirstDayOfWeek
     * @see #getMinimalDaysInFirstWeek
     */
    public static final int WEEK_OF_MONTH = 4;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * week number within the current year.  The first week of the year, as
     * defined by {@link #getFirstDayOfWeek()} and
     * {@link #getMinimalDaysInFirstWeek()}, has value 1.  Subclasses define
     * the value of {@link #WEEK_OF_YEAR} for days before the first week of
     * the year.
     * @see #getFirstDayOfWeek
     * @see #getMinimalDaysInFirstWeek
     */
    public static final int WEEK_OF_YEAR = 3;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * year. This is a calendar-specific value; see subclass documentation.
     */
    public static final int YEAR = 1;

    /**
     * <strong>[icu]</strong> Field number for <code>get()</code> and <code>set()</code>
     * indicating the extended year corresponding to the
     * {@link #WEEK_OF_YEAR} field.  This may be one greater or less
     * than the value of {@link #EXTENDED_YEAR}.
     */
    public static final int YEAR_WOY = 17;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * raw offset from GMT in milliseconds.
     */
    public static final int ZONE_OFFSET = 15;

    private Calendar() {
    }
}
