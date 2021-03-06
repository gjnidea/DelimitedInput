# TextInputEditText with auto delimiter for android


Automatically place delimiter as the user types.

## Components

* <b>DelimitedInputEditText</b> - For general purpose. Contains `delimiter` and `member_count` attributes

    <i>attributes:</i>

	1) delimiter (String) - delimiter to use. e.g. `app:delimiter="-"`
    2) member_count (int) - delimiter will be added after typing this amount of characters. e.g. `app:member_count="3"` will yield `abc-def-g`

* <b>CurrencyInputEditText</b> - For currency inputs. As of now, only `Locale.US` type of currency is included.

	<i>public methods:</i>

    for sample input of `1,000`
    1) `getBigDecimal(): BigDecimal` - 1000 of type decimal
    2) `getStringInput(): String` - 1000 of type String

	You can use the `editText.text.toString()` to get `1,000`
	
	<i>attributes:</i>
	
	1) min_decimal_place (int) - specifies the minimum decimal place. Default value is `2`.
	2) max_decimal_place (int) - specifies the maximum decimal place. Default value is `2`.
	3) apply_decimal (boolean) - if false, will not apply the min/max decimal place. Default value is `true`

## Usage

Add in your root build.gradle:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Add in gradle

    implementation 'com.github.gjnidea:DelimitedInput:1.0.0'

Add the following in your layout:

For **DelimitedInputEditText**:

	<gj.delimitedinput.view.DelimitedInputEditText
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		app:delimiter="-"
		app:member_count="3" />

For **CurrencyInputEditText**:

	<gj.delimitedinput.view.CurrencyInputEditText
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
		app:min_decimal_place="3"
		app:max_decimal_place="3" />
