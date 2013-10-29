package com.uzabase.selenium.example

import org.specs2.specification.Step
import com.uzabase.phosphorus.elements.id
import com.uzabase.phosphorus.elements.Element
import com.uzabase.phosphorus.elements.text_
import com.uzabase.phosphorus.elements.Anchor
import com.uzabase.phosphorus.elements.RadioChoice
import com.uzabase.phosphorus.elements.CheckBox
import com.uzabase.phosphorus.elements.TextBox
import com.uzabase.phosphorus.SeleniumSpecification

class FormTest extends SeleniumSpecification { def is = s2"""
	
	=== フォーム入力のサンプル ===
	
	フォーム入力画面へ遷移										${s1}
		文字列入力用のテキストフィールドが存在する					${e1}
		「hoge」と入力する										${s2}
		「hoge」と表示されている									${e2}
		「foo」と追記する										${s3}
		「hogefoo」と表示されている								${e3}
		「2」のラジオボタンをクリックする							${s4}
		チェックボックスの全てにチェックを付ける					${s5}
"""

	lazy val stringText = TextBox(id("stringProperty"))
	lazy val radio = RadioChoice("numberRadioChoice")
	lazy val checkbox = CheckBox("numbersCheckGroup")

	def s1 = Step(Anchor("forminput").click)
	def e1 = stringText must beDisplayed
	def s2 = Step(stringText.input("hoge"))
	def e2 = stringText.value must beEqualTo("hoge")
	def s3 = Step(stringText.appending("foo"))
	def e3 = stringText.value must beEqualTo("hogefoo")
	def e4 = radio must beDisplayed
	def s4 = Step(radio.choice("3"))
	def s5 = Step{
		checkbox.choice("1")
		checkbox.choice("2")
		checkbox.choice("3")
	}
}