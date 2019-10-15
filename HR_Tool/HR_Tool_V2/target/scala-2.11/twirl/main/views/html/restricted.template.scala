
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._

/**/
object restricted extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[models.User,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(localUser: models.User = null):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.33*/("""

"""),_display_(/*3.2*/main(Messages("playauthenticate.navigation.restricted"), "restricted")/*3.72*/ {_display_(Seq[Any](format.raw/*3.74*/("""


        """),format.raw/*6.9*/("""<h1>"""),_display_(/*6.14*/Messages("playauthenticate.navigation.restricted")),format.raw/*6.64*/("""</h1>
    <p>
        """),_display_(/*8.10*/Messages("playauthenticate.restricted.secrets")),format.raw/*8.57*/("""
    """),format.raw/*9.5*/("""</p>
""")))}))}
  }

  def render(localUser:models.User): play.twirl.api.HtmlFormat.Appendable = apply(localUser)

  def f:((models.User) => play.twirl.api.HtmlFormat.Appendable) = (localUser) => apply(localUser)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Mon Jun 19 00:44:44 CEST 2017
                  SOURCE: C:/Users/Subhashini/Downloads/HrToolV2/adnan_changes/app/views/restricted.scala.html
                  HASH: ae323aef1a8d8462729d71b67baebfb010c902c3
                  MATRIX: 733->1|852->32|880->35|958->105|997->107|1034->118|1065->123|1135->173|1184->196|1251->243|1282->248
                  LINES: 26->1|29->1|31->3|31->3|31->3|34->6|34->6|34->6|36->8|36->8|37->9
                  -- GENERATED --
              */
          