$(function(){
    $('#cc').layout();
    
    $("#menutree").tree({
		onLoadError:function(){
		},
        onClick:function(node){
			if(node.id=="deptshow"){
				$("#content").load("showdept.html",function(){
					$("#menutree").tree("reload");
				});
			}
        }
    });
	
    
});