package android.media;

/**
 * This is a class for reading and writing Exif tags in various image file formats.
 * <p>
 * <b>Note:</b> This class has known issues on some versions of Android. It is recommended to use
 * the <a href="{@docRoot}jetpack/androidx.html">AndroidX</a>
 * <a href="{@docRoot}reference/androidx/exifinterface/media/ExifInterface.html">ExifInterface
 * Library</a> since it offers a superset of the functionality of this class and is more easily
 * updateable. In addition to the functionality of this class, it supports parsing extra metadata
 * such as exposure and data compression information as well as setting extra metadata such as GPS
 * and datetime information.
 * <p>
 * Supported for reading: JPEG, PNG, WebP, HEIF, DNG, CR2, NEF, NRW, ARW, RW2, ORF, PEF, SRW, RAF,
 * AVIF.
 * <p>
 * Supported for writing: JPEG, PNG, WebP.
 * <p>
 * Note: JPEG and HEIF files may contain XMP data either inside the Exif data chunk or outside of
 * it. This class will search both locations for XMP data, but if XMP data exist both inside and
 * outside Exif, will favor the XMP data inside Exif over the one outside.
 * <p>
 */
public final class ExifInterface {
    public static final int ORIENTATION_FLIP_HORIZONTAL = 2;

    public static final int ORIENTATION_FLIP_VERTICAL = 4;

    public static final int ORIENTATION_NORMAL = 1;

    public static final int ORIENTATION_ROTATE_180 = 3;

    public static final int ORIENTATION_ROTATE_270 = 8;

    public static final int ORIENTATION_ROTATE_90 = 6;

    public static final int ORIENTATION_TRANSPOSE = 5;

    public static final int ORIENTATION_TRANSVERSE = 7;

    public static final int ORIENTATION_UNDEFINED = 0;

    /**
     * Constant used to indicate that the input stream contains only Exif data.
     * <p>
     * The format of the Exif-only data must follow the below structure:
     *     Exif Identifier Code ("Exif\0\0") + TIFF header + IFD data
     * See JEITA CP-3451C Section 4.5.2 and 4.5.4 specifications for more details.
     */
    public static final int STREAM_TYPE_EXIF_DATA_ONLY = 1;

    /**
     * Constant used to indicate that the input stream contains the full image data.
     * <p>
     * The format of the image data should follow one of the image formats supported by this class.
     */
    public static final int STREAM_TYPE_FULL_IMAGE_DATA = 0;

    /**
     * Type is double.
     *
     * @deprecated use {@link #TAG_F_NUMBER} instead
     */
    @Deprecated
    public static final String TAG_APERTURE = "FNumber";

    /**
     * Type is rational.
     */
    public static final String TAG_APERTURE_VALUE = "ApertureValue";

    /**
     * Type is String.
     */
    public static final String TAG_ARTIST = "Artist";

    /**
     * Type is int.
     */
    public static final String TAG_BITS_PER_SAMPLE = "BitsPerSample";

    /**
     * Type is rational.
     */
    public static final String TAG_BRIGHTNESS_VALUE = "BrightnessValue";

    /**
     * Type is String.
     */
    public static final String TAG_CFA_PATTERN = "CFAPattern";

    /**
     * Type is int.
     */
    public static final String TAG_COLOR_SPACE = "ColorSpace";

    /**
     * Type is String.
     */
    public static final String TAG_COMPONENTS_CONFIGURATION = "ComponentsConfiguration";

    /**
     * Type is rational.
     */
    public static final String TAG_COMPRESSED_BITS_PER_PIXEL = "CompressedBitsPerPixel";

    /**
     * Type is int.
     */
    public static final String TAG_COMPRESSION = "Compression";

    /**
     * Type is int.
     */
    public static final String TAG_CONTRAST = "Contrast";

    /**
     * Type is String.
     */
    public static final String TAG_COPYRIGHT = "Copyright";

    /**
     * Type is int.
     */
    public static final String TAG_CUSTOM_RENDERED = "CustomRendered";

    /**
     * Type is String.
     */
    public static final String TAG_DATETIME = "DateTime";

    /**
     * Type is String.
     */
    public static final String TAG_DATETIME_DIGITIZED = "DateTimeDigitized";

    /**
     * Type is String.
     */
    public static final String TAG_DATETIME_ORIGINAL = "DateTimeOriginal";

    /**
     * Type is int. DNG Specification 1.4.0.0. Section 4
     */
    public static final String TAG_DEFAULT_CROP_SIZE = "DefaultCropSize";

    /**
     * Type is String.
     */
    public static final String TAG_DEVICE_SETTING_DESCRIPTION = "DeviceSettingDescription";

    /**
     * Type is double.
     */
    public static final String TAG_DIGITAL_ZOOM_RATIO = "DigitalZoomRatio";

    /**
     * Type is int. DNG Specification 1.4.0.0. Section 4
     */
    public static final String TAG_DNG_VERSION = "DNGVersion";

    /**
     * Type is String.
     */
    public static final String TAG_EXIF_VERSION = "ExifVersion";

    /**
     * Type is double.
     */
    public static final String TAG_EXPOSURE_BIAS_VALUE = "ExposureBiasValue";

    /**
     * Type is rational.
     */
    public static final String TAG_EXPOSURE_INDEX = "ExposureIndex";

    /**
     * Type is int.
     */
    public static final String TAG_EXPOSURE_MODE = "ExposureMode";

    /**
     * Type is int.
     */
    public static final String TAG_EXPOSURE_PROGRAM = "ExposureProgram";

    /**
     * Type is double.
     */
    public static final String TAG_EXPOSURE_TIME = "ExposureTime";

    /**
     * Type is String.
     */
    public static final String TAG_FILE_SOURCE = "FileSource";

    /**
     * Type is int.
     */
    public static final String TAG_FLASH = "Flash";

    /**
     * Type is String.
     */
    public static final String TAG_FLASHPIX_VERSION = "FlashpixVersion";

    /**
     * Type is rational.
     */
    public static final String TAG_FLASH_ENERGY = "FlashEnergy";

    /**
     * Type is rational.
     */
    public static final String TAG_FOCAL_LENGTH = "FocalLength";

    /**
     * Type is int.
     */
    public static final String TAG_FOCAL_LENGTH_IN_35MM_FILM = "FocalLengthIn35mmFilm";

    /**
     * Type is int.
     */
    public static final String TAG_FOCAL_PLANE_RESOLUTION_UNIT = "FocalPlaneResolutionUnit";

    /**
     * Type is rational.
     */
    public static final String TAG_FOCAL_PLANE_X_RESOLUTION = "FocalPlaneXResolution";

    /**
     * Type is rational.
     */
    public static final String TAG_FOCAL_PLANE_Y_RESOLUTION = "FocalPlaneYResolution";

    /**
     * Type is double.
     */
    public static final String TAG_F_NUMBER = "FNumber";

    /**
     * Type is int.
     */
    public static final String TAG_GAIN_CONTROL = "GainControl";

    /**
     * The altitude (in meters) based on the reference in TAG_GPS_ALTITUDE_REF.
     * Type is rational.
     */
    public static final String TAG_GPS_ALTITUDE = "GPSAltitude";

    /**
     * 0 if the altitude is above sea level. 1 if the altitude is below sea
     * level. Type is int.
     */
    public static final String TAG_GPS_ALTITUDE_REF = "GPSAltitudeRef";

    /**
     * Type is String.
     */
    public static final String TAG_GPS_AREA_INFORMATION = "GPSAreaInformation";

    /**
     * Type is String.
     */
    public static final String TAG_GPS_DATESTAMP = "GPSDateStamp";

    /**
     * Type is rational.
     */
    public static final String TAG_GPS_DEST_BEARING = "GPSDestBearing";

    /**
     * Type is String.
     */
    public static final String TAG_GPS_DEST_BEARING_REF = "GPSDestBearingRef";

    /**
     * Type is rational.
     */
    public static final String TAG_GPS_DEST_DISTANCE = "GPSDestDistance";

    /**
     * Type is String.
     */
    public static final String TAG_GPS_DEST_DISTANCE_REF = "GPSDestDistanceRef";

    /**
     * Type is rational.
     */
    public static final String TAG_GPS_DEST_LATITUDE = "GPSDestLatitude";

    /**
     * Type is String.
     */
    public static final String TAG_GPS_DEST_LATITUDE_REF = "GPSDestLatitudeRef";

    /**
     * Type is rational.
     */
    public static final String TAG_GPS_DEST_LONGITUDE = "GPSDestLongitude";

    /**
     * Type is String.
     */
    public static final String TAG_GPS_DEST_LONGITUDE_REF = "GPSDestLongitudeRef";

    /**
     * Type is int.
     */
    public static final String TAG_GPS_DIFFERENTIAL = "GPSDifferential";

    /**
     * Type is rational.
     */
    public static final String TAG_GPS_DOP = "GPSDOP";

    /**
     * Type is rational.
     */
    public static final String TAG_GPS_IMG_DIRECTION = "GPSImgDirection";

    /**
     * Type is String.
     */
    public static final String TAG_GPS_IMG_DIRECTION_REF = "GPSImgDirectionRef";

    /**
     * Type is rational. Format is "num1/denom1,num2/denom2,num3/denom3".
     */
    public static final String TAG_GPS_LATITUDE = "GPSLatitude";

    /**
     * Type is String.
     */
    public static final String TAG_GPS_LATITUDE_REF = "GPSLatitudeRef";

    /**
     * Type is rational. Format is "num1/denom1,num2/denom2,num3/denom3".
     */
    public static final String TAG_GPS_LONGITUDE = "GPSLongitude";

    /**
     * Type is String.
     */
    public static final String TAG_GPS_LONGITUDE_REF = "GPSLongitudeRef";

    /**
     * Type is String.
     */
    public static final String TAG_GPS_MAP_DATUM = "GPSMapDatum";

    /**
     * Type is String.
     */
    public static final String TAG_GPS_MEASURE_MODE = "GPSMeasureMode";

    /**
     * Type is String. Name of GPS processing method used for location finding.
     */
    public static final String TAG_GPS_PROCESSING_METHOD = "GPSProcessingMethod";

    /**
     * Type is String.
     */
    public static final String TAG_GPS_SATELLITES = "GPSSatellites";

    /**
     * Type is rational.
     */
    public static final String TAG_GPS_SPEED = "GPSSpeed";

    /**
     * Type is String.
     */
    public static final String TAG_GPS_SPEED_REF = "GPSSpeedRef";

    /**
     * Type is String.
     */
    public static final String TAG_GPS_STATUS = "GPSStatus";

    /**
     * Type is String. Format is "hh:mm:ss".
     */
    public static final String TAG_GPS_TIMESTAMP = "GPSTimeStamp";

    /**
     * Type is rational.
     */
    public static final String TAG_GPS_TRACK = "GPSTrack";

    /**
     * Type is String.
     */
    public static final String TAG_GPS_TRACK_REF = "GPSTrackRef";

    /**
     * Type is String.
     */
    public static final String TAG_GPS_VERSION_ID = "GPSVersionID";

    /**
     * Type is String.
     */
    public static final String TAG_IMAGE_DESCRIPTION = "ImageDescription";

    /**
     * Type is int.
     */
    public static final String TAG_IMAGE_LENGTH = "ImageLength";

    /**
     * Type is String.
     */
    public static final String TAG_IMAGE_UNIQUE_ID = "ImageUniqueID";

    /**
     * Type is int.
     */
    public static final String TAG_IMAGE_WIDTH = "ImageWidth";

    /**
     * Type is String.
     */
    public static final String TAG_INTEROPERABILITY_INDEX = "InteroperabilityIndex";

    /**
     * Type is int.
     *
     * @deprecated use {@link #TAG_ISO_SPEED_RATINGS} instead
     */
    @Deprecated
    public static final String TAG_ISO = "ISOSpeedRatings";

    /**
     * Type is int.
     */
    public static final String TAG_ISO_SPEED_RATINGS = "ISOSpeedRatings";

    /**
     * Type is int.
     */
    public static final String TAG_JPEG_INTERCHANGE_FORMAT = "JPEGInterchangeFormat";

    /**
     * Type is int.
     */
    public static final String TAG_JPEG_INTERCHANGE_FORMAT_LENGTH = "JPEGInterchangeFormatLength";

    /**
     * Type is int.
     */
    public static final String TAG_LIGHT_SOURCE = "LightSource";

    /**
     * Type is String.
     */
    public static final String TAG_MAKE = "Make";

    /**
     * Type is String.
     */
    public static final String TAG_MAKER_NOTE = "MakerNote";

    /**
     * Type is rational.
     */
    public static final String TAG_MAX_APERTURE_VALUE = "MaxApertureValue";

    /**
     * Type is int.
     */
    public static final String TAG_METERING_MODE = "MeteringMode";

    /**
     * Type is String.
     */
    public static final String TAG_MODEL = "Model";

    /**
     * Type is int.
     */
    public static final String TAG_NEW_SUBFILE_TYPE = "NewSubfileType";

    /**
     * Type is String.
     */
    public static final String TAG_OECF = "OECF";

    /**
     *  <p>A tag used to record the offset from UTC (the time difference from Universal Time
     *  Coordinated including daylight saving time) of the time of DateTime tag. The format when
     *  recording the offset is "±HH:MM". The part of "±" shall be recorded as "+" or "-". When
     *  the offsets are unknown, all the character spaces except colons (":") should be filled
     *  with blank characters, or else the Interoperability field should be filled with blank
     *  characters. The character string length is 7 Bytes including NULL for termination. When
     *  the field is left blank, it is treated as unknown.</p>
     *
     *  <ul>
     *      <li>Tag = 36880</li>
     *      <li>Type = String</li>
     *      <li>Length = 7</li>
     *      <li>Default = None</li>
     *  </ul>
     */
    public static final String TAG_OFFSET_TIME = "OffsetTime";

    /**
     *  <p>A tag used to record the offset from UTC (the time difference from Universal Time
     *  Coordinated including daylight saving time) of the time of DateTimeDigitized tag. The format
     *  when recording the offset is "±HH:MM". The part of "±" shall be recorded as "+" or "-". When
     *  the offsets are unknown, all the character spaces except colons (":") should be filled
     *  with blank characters, or else the Interoperability field should be filled with blank
     *  characters. The character string length is 7 Bytes including NULL for termination. When
     *  the field is left blank, it is treated as unknown.</p>
     *
     *  <ul>
     *      <li>Tag = 36882</li>
     *      <li>Type = String</li>
     *      <li>Length = 7</li>
     *      <li>Default = None</li>
     *  </ul>
     */
    public static final String TAG_OFFSET_TIME_DIGITIZED = "OffsetTimeDigitized";

    /**
     *  <p>A tag used to record the offset from UTC (the time difference from Universal Time
     *  Coordinated including daylight saving time) of the time of DateTimeOriginal tag. The format
     *  when recording the offset is "±HH:MM". The part of "±" shall be recorded as "+" or "-". When
     *  the offsets are unknown, all the character spaces except colons (":") should be filled
     *  with blank characters, or else the Interoperability field should be filled with blank
     *  characters. The character string length is 7 Bytes including NULL for termination. When
     *  the field is left blank, it is treated as unknown.</p>
     *
     *  <ul>
     *      <li>Tag = 36881</li>
     *      <li>Type = String</li>
     *      <li>Length = 7</li>
     *      <li>Default = None</li>
     *  </ul>
     */
    public static final String TAG_OFFSET_TIME_ORIGINAL = "OffsetTimeOriginal";

    /**
     * Type is int. See Olympus Image Processing tags in http://www.exiv2.org/tags-olympus.html.
     */
    public static final String TAG_ORF_ASPECT_FRAME = "AspectFrame";

    /**
     * Type is int. See Olympus Camera Settings tags in http://www.exiv2.org/tags-olympus.html.
     */
    public static final String TAG_ORF_PREVIEW_IMAGE_LENGTH = "PreviewImageLength";

    /**
     * Type is int. See Olympus Camera Settings tags in http://www.exiv2.org/tags-olympus.html.
     */
    public static final String TAG_ORF_PREVIEW_IMAGE_START = "PreviewImageStart";

    /**
     * Type is undefined. See Olympus MakerNote tags in http://www.exiv2.org/tags-olympus.html.
     */
    public static final String TAG_ORF_THUMBNAIL_IMAGE = "ThumbnailImage";

    /**
     * Type is int.
     */
    public static final String TAG_ORIENTATION = "Orientation";

    /**
     * Type is int.
     */
    public static final String TAG_PHOTOMETRIC_INTERPRETATION = "PhotometricInterpretation";

    /**
     * Type is int.
     */
    public static final String TAG_PIXEL_X_DIMENSION = "PixelXDimension";

    /**
     * Type is int.
     */
    public static final String TAG_PIXEL_Y_DIMENSION = "PixelYDimension";

    /**
     * Type is int.
     */
    public static final String TAG_PLANAR_CONFIGURATION = "PlanarConfiguration";

    /**
     * Type is rational.
     */
    public static final String TAG_PRIMARY_CHROMATICITIES = "PrimaryChromaticities";

    /**
     * Type is rational.
     */
    public static final String TAG_REFERENCE_BLACK_WHITE = "ReferenceBlackWhite";

    /**
     * Type is String.
     */
    public static final String TAG_RELATED_SOUND_FILE = "RelatedSoundFile";

    /**
     * Type is int.
     */
    public static final String TAG_RESOLUTION_UNIT = "ResolutionUnit";

    /**
     * Type is int.
     */
    public static final String TAG_ROWS_PER_STRIP = "RowsPerStrip";

    /**
     * Type is int. See PanasonicRaw tags in
     * http://www.sno.phy.queensu.ca/~phil/exiftool/TagNames/PanasonicRaw.html
     */
    public static final String TAG_RW2_ISO = "ISO";

    /**
     * Type is undefined. See PanasonicRaw tags in
     * http://www.sno.phy.queensu.ca/~phil/exiftool/TagNames/PanasonicRaw.html
     */
    public static final String TAG_RW2_JPG_FROM_RAW = "JpgFromRaw";

    /**
     * Type is int. See PanasonicRaw tags in
     * http://www.sno.phy.queensu.ca/~phil/exiftool/TagNames/PanasonicRaw.html
     */
    public static final String TAG_RW2_SENSOR_BOTTOM_BORDER = "SensorBottomBorder";

    /**
     * Type is int. See PanasonicRaw tags in
     * http://www.sno.phy.queensu.ca/~phil/exiftool/TagNames/PanasonicRaw.html
     */
    public static final String TAG_RW2_SENSOR_LEFT_BORDER = "SensorLeftBorder";

    /**
     * Type is int. See PanasonicRaw tags in
     * http://www.sno.phy.queensu.ca/~phil/exiftool/TagNames/PanasonicRaw.html
     */
    public static final String TAG_RW2_SENSOR_RIGHT_BORDER = "SensorRightBorder";

    /**
     * Type is int. See PanasonicRaw tags in
     * http://www.sno.phy.queensu.ca/~phil/exiftool/TagNames/PanasonicRaw.html
     */
    public static final String TAG_RW2_SENSOR_TOP_BORDER = "SensorTopBorder";

    /**
     * Type is int.
     */
    public static final String TAG_SAMPLES_PER_PIXEL = "SamplesPerPixel";

    /**
     * Type is int.
     */
    public static final String TAG_SATURATION = "Saturation";

    /**
     * Type is int.
     */
    public static final String TAG_SCENE_CAPTURE_TYPE = "SceneCaptureType";

    /**
     * Type is String.
     */
    public static final String TAG_SCENE_TYPE = "SceneType";

    /**
     * Type is int.
     */
    public static final String TAG_SENSING_METHOD = "SensingMethod";

    /**
     * Type is int.
     */
    public static final String TAG_SHARPNESS = "Sharpness";

    /**
     * Type is rational.
     */
    public static final String TAG_SHUTTER_SPEED_VALUE = "ShutterSpeedValue";

    /**
     * Type is String.
     */
    public static final String TAG_SOFTWARE = "Software";

    /**
     * Type is String.
     */
    public static final String TAG_SPATIAL_FREQUENCY_RESPONSE = "SpatialFrequencyResponse";

    /**
     * Type is String.
     */
    public static final String TAG_SPECTRAL_SENSITIVITY = "SpectralSensitivity";

    /**
     * Type is int.
     */
    public static final String TAG_STRIP_BYTE_COUNTS = "StripByteCounts";

    /**
     * Type is int.
     */
    public static final String TAG_STRIP_OFFSETS = "StripOffsets";

    /**
     * Type is int.
     */
    public static final String TAG_SUBFILE_TYPE = "SubfileType";

    /**
     * Type is int.
     */
    public static final String TAG_SUBJECT_AREA = "SubjectArea";

    /**
     * Type is double.
     */
    public static final String TAG_SUBJECT_DISTANCE = "SubjectDistance";

    /**
     * Type is int.
     */
    public static final String TAG_SUBJECT_DISTANCE_RANGE = "SubjectDistanceRange";

    /**
     * Type is int.
     */
    public static final String TAG_SUBJECT_LOCATION = "SubjectLocation";

    /**
     * Type is String.
     */
    public static final String TAG_SUBSEC_TIME = "SubSecTime";

    /**
     * Type is String.
     *
     * @deprecated use {@link #TAG_SUBSEC_TIME_DIGITIZED} instead
     */
    @Deprecated
    public static final String TAG_SUBSEC_TIME_DIG = "SubSecTimeDigitized";

    /**
     * Type is String.
     */
    public static final String TAG_SUBSEC_TIME_DIGITIZED = "SubSecTimeDigitized";

    /**
     * Type is String.
     *
     * @deprecated use {@link #TAG_SUBSEC_TIME_ORIGINAL} instead
     */
    @Deprecated
    public static final String TAG_SUBSEC_TIME_ORIG = "SubSecTimeOriginal";

    /**
     * Type is String.
     */
    public static final String TAG_SUBSEC_TIME_ORIGINAL = "SubSecTimeOriginal";

    /**
     * Type is int.
     */
    public static final String TAG_THUMBNAIL_IMAGE_LENGTH = "ThumbnailImageLength";

    /**
     * Type is int.
     */
    public static final String TAG_THUMBNAIL_IMAGE_WIDTH = "ThumbnailImageWidth";

    /**
     * Type is int.
     */
    public static final String TAG_THUMBNAIL_ORIENTATION = "ThumbnailOrientation";

    /**
     * Type is int.
     */
    public static final String TAG_TRANSFER_FUNCTION = "TransferFunction";

    /**
     * Type is String.
     */
    public static final String TAG_USER_COMMENT = "UserComment";

    /**
     * Type is int.
     */
    public static final String TAG_WHITE_BALANCE = "WhiteBalance";

    /**
     * Type is rational.
     */
    public static final String TAG_WHITE_POINT = "WhitePoint";

    /**
     * Type is byte[]. See <a href=
     * "https://en.wikipedia.org/wiki/Extensible_Metadata_Platform">Extensible
     * Metadata Platform (XMP)</a> for details on contents.
     */
    public static final String TAG_XMP = "Xmp";

    /**
     * Type is rational.
     */
    public static final String TAG_X_RESOLUTION = "XResolution";

    /**
     * Type is rational.
     */
    public static final String TAG_Y_CB_CR_COEFFICIENTS = "YCbCrCoefficients";

    /**
     * Type is int.
     */
    public static final String TAG_Y_CB_CR_POSITIONING = "YCbCrPositioning";

    /**
     * Type is int.
     */
    public static final String TAG_Y_CB_CR_SUB_SAMPLING = "YCbCrSubSampling";

    /**
     * Type is rational.
     */
    public static final String TAG_Y_RESOLUTION = "YResolution";

    public static final int WHITEBALANCE_AUTO = 0;

    public static final int WHITEBALANCE_MANUAL = 1;

    private ExifInterface() {
    }
}
