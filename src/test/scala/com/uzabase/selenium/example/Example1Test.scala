package com.uzabase.selenium.example

import com.uzabase.selenium._
import org.specs2.specification.Step
import org.openqa.selenium.support.ui.WebDriverWait
import com.uzabase.phosphorus.elements.DropDown
import com.uzabase.phosphorus.elements.name
import com.uzabase.phosphorus.elements.text_
import com.uzabase.phosphorus.elements.DropDownOption
import com.uzabase.phosphorus.elements.Anchor
import com.uzabase.phosphorus.SeleniumSpecification

class Example1Test extends SeleniumSpecification { def is = s2"""
	
	=== Wicket Exampleページの仕様 ===
	
	・helloworld																	${e1}
	・echo																			${e2}
	・forminput																	${e3}
	・compref																		${e4}
	・images																		${e5}
	
	などのリンクがトップページに存在する。
	
	1.ajaxのリンクをクリックすると													${s1}
		Ajaxコンポーネントのページに遷移する											${e6}

	2.Drop Dwon Choice Exampleのリンクをクリックすると							${s2}
		Ajaxを利用したDropDownコンポーネントのページに遷移する						${e7}

		2-1.自動車メーカー名のDropDownには
			「選んでください、AUDI、FORD、CADILLAC」が表示されている					${e8}
			
			AUDIを選択すると															${s3}
			車種のDropDownには「選んでください、A4、A6、TT」	が選択可能となる			${e9}
			
		
		
	
"""
	
	lazy val ajaxLink = Anchor(text_("ajax"))
	lazy val first = DropDown(name("makes"))
	lazy val secound = DropDown(name("models"))
	def e1 = Anchor("helloworld") must beDisplayed
	def e2 = Anchor("echo") must beDisplayed
	def e3 = Anchor("forminput") must beDisplayed
	def e4 = Anchor("compref") must beDisplayed
	def e5 = Anchor("images") must beDisplayed
	def s1 = Step(ajaxLink.click)
	def e6 = title must beEqualTo("Wicket Examples - Ajax")
	def s2 = Step(Anchor("Drop Down Choice Example").click)
	def e7 = first must beDisplayed
	def e8 = first must haveOption("選んでください","AUDI","FORD","CADILLAC")
	def s3 = Step(first.select(text_("AUDI")))
	def e9 = {
		DropDownOption(text_("A4"),driverWait(10))
		secound must beDisplayed
		secound must haveOption("選んでください","A4","A6","TT")
	}
}