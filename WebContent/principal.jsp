<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@page import="org.formation.zoo.service.CagePOJO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bienvenue DANS le ZOO</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<header>
<img class="logoGauche" alt="" src="images/logo.png"/>
<img class="logoDroit" alt="" src="images/logo.png"/><p>Le zoo en folie</p>
</header>
<form name="fzoo_devorer" action="devorer" method="GET">
<nav>
<ul class="m">
	<li class="m"><a href="nourrir">TOUT le monde mange (defaut)</a></li>
	<li class="m"><a href="#" onClick="fzoo_devorer.submit();">FAIRE manger les animaux selectionnes</a></li>
	<li class="m"><a id="btnAjouter" href="#">Ajouter un animal</a></li>
	<li class="m"><a href="#">Supprimer un animal</a></li>
</ul>
</nav>
<article>
<%
List<CagePOJO> zanimaux = null;
zanimaux =  (List<CagePOJO>) request.getAttribute("lesCages");
String texte = null;
%>
<img  alt="mon zoo" src="images/plan.gif">
<% for(int i=0; i<zanimaux.size(); i++){
	texte = String.join("", "<div id=\"animal",Integer.toString(i),"\" style=\"position:absolute;top:",
		Integer.toString(zanimaux.get(i).getY()),"px;left:",Integer.toString(zanimaux.get(i).getX()),"px\">");
	out.print(texte);
	texte = String.join("", "<img alt=\"\" src=\"",zanimaux.get(i).getImage(),"\" class=\"animal\"/>");
	out.print(texte);
	texte = String.join("", "<div class=\"afficheAnimal\" >",zanimaux.get(i).getPancarte(),"</div>");
	out.print(texte);
	
	texte = String.join("", "<div class=\"mangeur\"><input type=\"radio\" value=\"",Integer.toString(i),"\" id=\"mangeur",
			Integer.toString(i),"\" name=\"mangeur\"><label for=\"mangeur",Integer.toString(i),"\">Mangeur</label></div>");
	out.print(texte);
	texte = String.join("", "<div class=\"mange\"><input type=\"radio\" value=\"",Integer.toString(i),"\" id=\"mange",
			Integer.toString(i),"\" name=\"mange\"><label for=\"mange",Integer.toString(i),"\">Mange</label></div></div>");
	out.print(texte);
}
%>
</article>
</form>

<form name="fzoo_creer" action="creer" method="GET">
<table id="formulaire_creer" style="display: none;">
	<thead>
		<tr>
            <th colspan="2">Ajouter animal</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>type d'animal</td>
            <td>
            	<select name="typeAnimal" id="typeAnimal">
  				  	<option value="Lion">Lion</option>
    				<option value="Gazelle">Gazelle</option>
    				<option value="Singe">Singe</option>
				</select>
            </td>
        </tr>
        <tr>
            <td>nom:</td>
            <td><input type="text" name="nom" placeholder="ex: Miti" required></td>
        </tr>
		<tr>
            <td>age:</td>
            <td><input type="number" name="age" placeholder="entier" min="0" required></td>
        </tr>
		<tr>
            <td>poids:</td>
            <td><input type="number" name="poids" placeholder="decimal" min="0" required></td>
        </tr>
        <tr id="champ_lgCornes" style="display: none;">
            <td>longueur cornes:</td>
            <td><input type="number" name="lgCornes" value="0" placeholder="entier" step="0.01" min="0" required></td>
        </tr>
		<tr>
            <td>cage n°:</td>
            <td><input type="number" name="numCage" placeholder="entier" min="0" required></td>
        </tr>
        <tr>
        	<td><button id="btnCreer" onClick="fzoo_creer.submit();">creer</button></td>
        	<td><button id="btnAnnuler">annuler</button></td>
       	</tr>
    </tbody>
</table>
</form>
<footer>
Etat en temps reel : 
<% if(session.getAttribute("etat") != null) {
	texte = String.join("", "<span id=\"etat\">",session.getAttribute("etat").toString(),"</span>");
	out.print(texte);
	session.removeAttribute("etat");
}
%>
</footer>
</form>
<script type="text/javascript">
	document.querySelector("#typeAnimal").addEventListener("change", function(e) {
        let val = e.target.value;
        if(val == "Gazelle")
        {
           document.getElementById("champ_lgCornes").style = "display: visible;";
        }
        else
		{
        	document.getElementById("champ_lgCornes").style = "display: none;";
    	}
	});

	document.querySelector("#btnAjouter").addEventListener("click", function(e) {
		e.preventDefault();
		document.getElementById("formulaire_creer").style = "display: visible;";
	});

	document.querySelector("#btnAnnuler").addEventListener("click", function(e) {
		e.preventDefault();
		document.getElementById("formulaire_creer").style = "display: none;";
	});
</script>
</body>
</html>
