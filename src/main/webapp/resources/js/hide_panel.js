//tabbed panels

// declare globals to hold all the links and all the panel elements
var tabLinks = new Array();
var hidingPanel;

window.onload=function() {
	
//	var fancy_forms = document.getElementsByClassName("fancy-form");
//	
//	for( var i = 0, len = fancy_forms.length; i < len; ++i ) {
//		var form = fancy_forms[i];
//		
//	}
	
    
    // when the page loads, grab the li elements
    var tmp = document.getElementById("fancy-form").getElementsByTagName("input");
	for(var i = 0, len = tmp.length; i < len; ++i ) {
		if( tmp[i].type == "checkbox" )
			tabLinks.push( tmp[i] );
	}
    
	// Now get all the tab panel container divs
	hidingPanel = document.getElementById("hidingPanel");

	displayPanel();
    
    // attach event listener to links using onclick and onfocus, fire the displayPanel function, return false to disable the link
    for (var i = 0; i < tabLinks.length; i++) {
    	
        tabLinks[i].onclick = displayPanel;
        tabLinks[i].onfocus = displayPanel;
    }
}

function displayPanel() {
	var activate = false;
	for(var i = 0, len = tabLinks.length; i < len; ++i ) {
		if( tabLinks[i].checked )
			activate = true;
	}
	if( activate ) {
		hidingPanel.classList.add("active");
		hidingPanel.style.display = "block";
	} else {
		hidingPanel.classList.remove("active");
		hidingPanel.style.display = "none";
	}
}
