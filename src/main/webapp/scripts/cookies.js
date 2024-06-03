function acceptCookies(){
		localStorage.setItem("cookiesAccepted","true")
		let cookiesContainer = document.getElementById("CookiesBox")
		cookiesContainer.style.display = 'none'
	}
	
	function declineCookies(){
		localStorage.setItem("cookiesAccepted","false");
		let cookiesContainer = document.getElementById("CookiesBox")
		cookiesContainer.style.display = 'none'
	}
	
	document.addEventListener("DOMContentLoaded",function(){
		let element = localStorage.getItem('cookiesAccepted')
		if(element === "true"){
			document.getElementById("CookiesBox").style.display = 'none'
		}else
			document.getElementById("CookiesBox").style.display = 'block'
				
	})