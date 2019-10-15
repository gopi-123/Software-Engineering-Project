
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
object login extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(loginForm: Form[_]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._
import com.feth.play.module.pa.views.html._

Seq[Any](format.raw/*1.22*/("""

"""),format.raw/*6.1*/("""
"""),_display_(/*7.2*/main(Messages("playauthenticate.login.title"),"login")/*7.56*/ {_display_(Seq[Any](format.raw/*7.58*/("""

  """),format.raw/*9.3*/("""<div class="row">
    <div class="col-sm-4 col-sm-offset-4" style="padding-top: 5%" align="center">
        <img class="user-icon" src=""""),_display_(/*11.38*/routes/*11.44*/.Assets.at("images/user-icon.png")),format.raw/*11.78*/("""" alt="" style="width:90px; height: 90px;" align="center">
      <h1></h1>
    </div>
  </div>

  <div id="login" class="col-sm-6 col-sm-offset-4">

    <div class="col-sm-6 col-sm-offset-4" align="center">
      """),format.raw/*19.43*/("""
    	"""),_display_(/*20.7*/helper/*20.13*/.form(routes.Application.doLogin)/*20.46*/ {_display_(Seq[Any](format.raw/*20.48*/("""

        """),_display_(/*22.10*/if(loginForm.hasGlobalErrors)/*22.39*/ {_display_(Seq[Any](format.raw/*22.41*/("""
          """),format.raw/*23.11*/("""<p class="error">
            <span class="label label-important">"""),_display_(/*24.50*/loginForm/*24.59*/.globalError.message),format.raw/*24.79*/("""</span>
          </p>
        """)))}),format.raw/*26.10*/("""

        """),_display_(/*28.10*/_emailPartial(loginForm)),format.raw/*28.34*/("""

        """),_display_(/*30.10*/inputPassword(
          loginForm("password"),
          '_showConstraints -> false,
          '_label -> Messages("playauthenticate.login.password.placeholder")
        )),format.raw/*34.10*/("""

        """),format.raw/*36.9*/("""<input type="submit" value=""""),_display_(/*36.38*/Messages("playauthenticate.login.now")),format.raw/*36.76*/("""" class="btn btn-primary"><br>


        <a href="javascript:void(0);" onclick="window.location.href = jsRoutes.controllers.Signup.forgotPassword($('#email').val() || null).absoluteURL();">"""),_display_(/*39.158*/Messages("playauthenticate.login.forgot.password")),format.raw/*39.208*/("""</a>
            """)))}),format.raw/*40.14*/("""
    """),format.raw/*41.5*/("""</div>

<!--     <div class="span3">
      """),_display_(/*44.8*/Messages("playauthenticate.login.oauth")),format.raw/*44.48*/("""
      """),format.raw/*45.48*/("""
      """),_display_(/*46.8*/_providerPartial(skipCurrent=false)),format.raw/*46.43*/("""
      """),_display_(/*47.8*/providerAvailable("basic")/*47.34*/ { available: Boolean =>_display_(Seq[Any](format.raw/*47.58*/("""
          """),_display_(/*48.12*/if(available)/*48.25*/ {_display_(Seq[Any](format.raw/*48.27*/("""
              """),format.raw/*49.15*/("""<br>
              <a href=""""),_display_(/*50.25*/com/*50.28*/.feth.play.module.pa.controllers.routes.Authenticate.authenticate("basic")),format.raw/*50.102*/("""">"""),_display_(/*50.105*/Messages("playauthenticate.login.basic")),format.raw/*50.145*/("""</a>
          """)))}),format.raw/*51.12*/("""
      """)))}),format.raw/*52.8*/("""
    """),format.raw/*53.5*/("""</div>
 -->
  </div>

""")))}),format.raw/*57.2*/("""
"""))}
  }

  def render(loginForm:Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
}): play.twirl.api.HtmlFormat.Appendable = apply(loginForm)

  def f:((Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
}) => play.twirl.api.HtmlFormat.Appendable) = (loginForm) => apply(loginForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sun Jun 18 20:57:47 CEST 2017
                  SOURCE: C:/Users/Subhashini/Downloads/HrToolV2/adnan_changes/app/views/login.scala.html
                  HASH: b067a36622158a1478036007c871e359bdba384d
                  MATRIX: 797->1|997->21|1025->119|1052->121|1114->175|1153->177|1183->181|1347->318|1362->324|1417->358|1658->607|1691->614|1706->620|1748->653|1788->655|1826->666|1864->695|1904->697|1943->708|2037->775|2055->784|2096->804|2159->836|2197->847|2242->871|2280->882|2473->1054|2510->1064|2566->1093|2625->1131|2843->1321|2915->1371|2964->1389|2996->1394|3066->1438|3127->1478|3162->1526|3196->1534|3252->1569|3286->1577|3321->1603|3383->1627|3422->1639|3444->1652|3484->1654|3527->1669|3583->1698|3595->1701|3691->1775|3722->1778|3784->1818|3831->1834|3869->1842|3901->1847|3954->1870
                  LINES: 28->1|33->1|35->6|36->7|36->7|36->7|38->9|40->11|40->11|40->11|48->19|49->20|49->20|49->20|49->20|51->22|51->22|51->22|52->23|53->24|53->24|53->24|55->26|57->28|57->28|59->30|63->34|65->36|65->36|65->36|68->39|68->39|69->40|70->41|73->44|73->44|74->45|75->46|75->46|76->47|76->47|76->47|77->48|77->48|77->48|78->49|79->50|79->50|79->50|79->50|79->50|80->51|81->52|82->53|86->57
                  -- GENERATED --
              */
          