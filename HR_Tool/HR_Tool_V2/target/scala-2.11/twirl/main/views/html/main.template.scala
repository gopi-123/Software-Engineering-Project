
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
object main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[String,String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String, nav: String = "")(content: Html):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import be.objectify.deadbolt.java.views.html._
import be.objectify.deadbolt.core.utils.TemplateUtils._

Seq[Any](format.raw/*1.50*/("""

"""),format.raw/*5.1*/("""
"""),format.raw/*6.1*/("""<!DOCTYPE html>
<html lang=""""),_display_(/*7.14*/lang()/*7.20*/.code()),format.raw/*7.27*/("""">
  <head>
    <title>"""),_display_(/*9.13*/title),format.raw/*9.18*/("""</title>

    <!-- Le meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Play Authenticate - an authentication module for the Play! Framework 2.0">
    <meta name="author" content="The Play Authenticate Team">

    <!-- Le scripts -->
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<script src=""""),_display_(/*19.16*/routes/*19.22*/.Assets.at("js/bootstrap.min.js")),format.raw/*19.55*/(""""></script>
	<script src=""""),_display_(/*20.16*/routes/*20.22*/.Application.jsRoutes),format.raw/*20.43*/("""" defer="defer"></script>

    <!-- Le styles -->
    <link href=""""),_display_(/*23.18*/routes/*23.24*/.Assets.at("css/main.css")),format.raw/*23.50*/("""" rel="stylesheet">
      <link href=""""),_display_(/*24.20*/routes/*24.26*/.Assets.at("css/styles.css")),format.raw/*24.54*/("""" rel="stylesheet">
    <link href=""""),_display_(/*25.18*/routes/*25.24*/.Assets.at("css/bootstrap.min.css")),format.raw/*25.59*/("""" rel="stylesheet">


    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

  </head>

  <body>

    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">

          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
            <img class="logo" src=""""),_display_(/*46.37*/routes/*46.43*/.Assets.at("images/uds-logo.png")),format.raw/*46.76*/("""" alt="Universität des Saarlandes" style="width:177px;height:71px;">
          <a class="brand" href=""""),_display_(/*47.35*/routes/*47.41*/.Application.index()),format.raw/*47.61*/("""">HR Tool Interne Positionsvermittlung</a>

          <div class="btn-group pull-right">
          """),_display_(/*50.12*/subjectPresentOr()/*50.30*/ {_display_(Seq[Any](format.raw/*50.32*/("""
          	"""),_display_(/*51.13*/defining(Application.getLocalUser(session()))/*51.58*/ { user =>_display_(Seq[Any](format.raw/*51.68*/("""
            """),format.raw/*52.13*/("""<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
              <i class="icon-user"></i> """),_display_(/*53.42*/user/*53.46*/.name),format.raw/*53.51*/("""
              """),format.raw/*54.15*/("""<span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
              <li><a href=""""),_display_(/*57.29*/routes/*57.35*/.Application.profile()),format.raw/*57.57*/("""">"""),_display_(/*57.60*/Messages("playauthenticate.navigation.profile")),format.raw/*57.107*/("""</a></li>
              <li><a href=""""),_display_(/*58.29*/routes/*58.35*/.Account.link()),format.raw/*58.50*/("""">"""),_display_(/*58.53*/Messages("playauthenticate.navigation.link_more")),format.raw/*58.102*/("""</a></li>
              <li class="divider"></li>
              <li><a href=""""),_display_(/*60.29*/com/*60.32*/.feth.play.module.pa.controllers.routes.Authenticate.logout()),format.raw/*60.93*/(""""><i class="icon-off"></i> """),_display_(/*60.121*/Messages("playauthenticate.navigation.logout")),format.raw/*60.167*/("""</a></li>
            </ul>
            """)))}),format.raw/*62.14*/("""
          """)))}/*63.12*/{_display_(Seq[Any](format.raw/*63.13*/("""
            """),format.raw/*64.13*/("""<a href=""""),_display_(/*64.23*/routes/*64.29*/.Application.login()),format.raw/*64.49*/("""" class="btn btn-primary btn-mini """),_display_(/*64.84*/("disabled".when(nav == "login"))),format.raw/*64.117*/("""">"""),_display_(/*64.120*/Messages("playauthenticate.navigation.login")),format.raw/*64.165*/("""</a>
          """)))}),format.raw/*65.12*/("""
          """),format.raw/*66.11*/("""</div>


          <div class="nav-collapse">
            <ul class="nav">
              <li class=""""),_display_(/*71.27*/("active".when(nav == ""))),format.raw/*71.53*/(""""><a href=""""),_display_(/*71.65*/routes/*71.71*/.Application.index()),format.raw/*71.91*/("""">"""),_display_(/*71.94*/Messages("playauthenticate.navigation.home")),format.raw/*71.138*/("""</a></li>
              <li class=""""),_display_(/*72.27*/("active".when(nav == "restricted"))),format.raw/*72.63*/(""""><a href=""""),_display_(/*72.75*/routes/*72.81*/.Application.restricted()),format.raw/*72.106*/("""">"""),_display_(/*72.109*/Messages("playauthenticate.navigation.restricted")),format.raw/*72.159*/("""</a></li>

                """),_display_(/*74.18*/subjectNotPresent()/*74.37*/ {_display_(Seq[Any](format.raw/*74.39*/("""
			    	"""),format.raw/*75.9*/("""<li class=""""),_display_(/*75.21*/("active".when(nav == "signup"))),format.raw/*75.53*/(""""><a href=""""),_display_(/*75.65*/routes/*75.71*/.Application.signup()),format.raw/*75.92*/("""">"""),_display_(/*75.95*/Messages("playauthenticate.navigation.signup")),format.raw/*75.141*/("""</a></li>
			    """)))}),format.raw/*76.9*/("""
            """),format.raw/*77.13*/("""</ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container">
	    """),_display_(/*84.7*/if(flash.contains(Application.FLASH_ERROR_KEY))/*84.54*/ {_display_(Seq[Any](format.raw/*84.56*/("""
	      """),format.raw/*85.8*/("""<div class="alert alert-error">
	      	"""),_display_(/*86.10*/flash()/*86.17*/.get(Application.FLASH_ERROR_KEY)),format.raw/*86.50*/("""
	      """),format.raw/*87.8*/("""</div>
	    """)))}),format.raw/*88.7*/("""
	    """),_display_(/*89.7*/if(flash.contains(Application.FLASH_MESSAGE_KEY))/*89.56*/ {_display_(Seq[Any](format.raw/*89.58*/("""
	      """),format.raw/*90.8*/("""<div class="alert alert-success">
	      	"""),_display_(/*91.10*/flash()/*91.17*/.get(Application.FLASH_MESSAGE_KEY)),format.raw/*91.52*/("""
	      """),format.raw/*92.8*/("""</div>
	    """)))}),format.raw/*93.7*/("""
		"""),_display_(/*94.4*/content),format.raw/*94.11*/("""

      """),format.raw/*96.7*/("""<div class="Ourfooter">
        <p style="padding-top: 8px; padding-left: 34%;"><strong>&copy; 2017 HR Tool Interne Positionvermittlung | Universität des Saarlandes. Version 1.0.</strong></p>
      </div>
    </div> <!-- /container -->

  </body>
</html>
"""))}
  }

  def render(title:String,nav:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title,nav)(content)

  def f:((String,String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title,nav) => (content) => apply(title,nav)(content)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sun Jun 18 19:40:31 CEST 2017
                  SOURCE: C:/Users/Subhashini/Downloads/HrToolV2/adnan_changes/app/views/main.scala.html
                  HASH: 951d7a1b0b142aeeb231066a0e616ea89dd637d6
                  MATRIX: 734->1|972->49|1000->156|1027->157|1082->186|1096->192|1123->199|1173->223|1198->228|1657->660|1672->666|1726->699|1780->726|1795->732|1837->753|1931->820|1946->826|1993->852|2059->891|2074->897|2123->925|2187->962|2202->968|2258->1003|2889->1607|2904->1613|2958->1646|3088->1749|3103->1755|3144->1775|3271->1875|3298->1893|3338->1895|3378->1908|3432->1953|3480->1963|3521->1976|3653->2081|3666->2085|3692->2090|3735->2105|3874->2217|3889->2223|3932->2245|3962->2248|4031->2295|4096->2333|4111->2339|4147->2354|4177->2357|4248->2406|4353->2484|4365->2487|4447->2548|4503->2576|4571->2622|4643->2663|4674->2675|4713->2676|4754->2689|4791->2699|4806->2705|4847->2725|4909->2760|4964->2793|4995->2796|5062->2841|5109->2857|5148->2868|5276->2969|5323->2995|5362->3007|5377->3013|5418->3033|5448->3036|5514->3080|5577->3116|5634->3152|5673->3164|5688->3170|5735->3195|5766->3198|5838->3248|5893->3276|5921->3295|5961->3297|5997->3306|6036->3318|6089->3350|6128->3362|6143->3368|6185->3389|6215->3392|6283->3438|6331->3456|6372->3469|6517->3588|6573->3635|6613->3637|6648->3645|6716->3686|6732->3693|6786->3726|6821->3734|6864->3747|6897->3754|6955->3803|6995->3805|7030->3813|7100->3856|7116->3863|7172->3898|7207->3906|7250->3919|7280->3923|7308->3930|7343->3938
                  LINES: 26->1|30->1|32->5|33->6|34->7|34->7|34->7|36->9|36->9|46->19|46->19|46->19|47->20|47->20|47->20|50->23|50->23|50->23|51->24|51->24|51->24|52->25|52->25|52->25|73->46|73->46|73->46|74->47|74->47|74->47|77->50|77->50|77->50|78->51|78->51|78->51|79->52|80->53|80->53|80->53|81->54|84->57|84->57|84->57|84->57|84->57|85->58|85->58|85->58|85->58|85->58|87->60|87->60|87->60|87->60|87->60|89->62|90->63|90->63|91->64|91->64|91->64|91->64|91->64|91->64|91->64|91->64|92->65|93->66|98->71|98->71|98->71|98->71|98->71|98->71|98->71|99->72|99->72|99->72|99->72|99->72|99->72|99->72|101->74|101->74|101->74|102->75|102->75|102->75|102->75|102->75|102->75|102->75|102->75|103->76|104->77|111->84|111->84|111->84|112->85|113->86|113->86|113->86|114->87|115->88|116->89|116->89|116->89|117->90|118->91|118->91|118->91|119->92|120->93|121->94|121->94|123->96
                  -- GENERATED --
              */
          