const toggleSidebar = () =>{

    if($('.sidebar').is(":visible")){
            //true
        $(".sidebar").css("display","none");
        $(".content").css("margin-left","0%");

    }else{
        //false
        $(".sidebar").css("display","block");
        $(".content").css("margin-left","20%");

    }
    
}



 function detelet(id){
		 swal({
      title: "Are you sure?",
      text: "Once deleted, you will not be able to recover this imaginary file!",
      icon: "warning",
      buttons: true,
      dangerMode: true,
     })
    .then((willDelete) => {
     if (willDelete) {
       window.location="/user/delete/"+id
     } else {
     swal("Your imaginary file is safe!");
    }
     });
	 
}

const search = () =>{
	
	
	
	let query = $("#search-input").val();

    if (query==""){
		
        $(".search-result").hide();
    
    }else{
        //search

      

      //sending request to server
      let url=`http://localhost:8080/search/${query}`;
      fetch(url).then((Response) => {
      return Response.json();
      }).then(data=>{
         //data ...... accesss
         
         let text=`<div class='list-group'>`
           data.forEach((contact) => {
            text+=`<a href='/user/view-contact/+${contact.id}' class='list-group-item list-group-action'>${contact.name} </a>`
          });
         text += `</div>`;
         $(".search-result").html(text); 
         $(".search-result").show(); 
     });
     

    }
	
	
}







