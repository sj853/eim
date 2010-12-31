$(function(){
    $('#cc').layout();
    
    $("#menutree").tree({
		onLoadError:function(){
		},
        onClick:function(node){
			if(node.id=="deptshow"){
				$("#content").load(node.attributes.url,function(){
					$("#menutree").tree("reload");
				});
			}
			else if(node.id=="exit"){
				$.post(node.attributes.url,function(){
					window.opener = null;
                window.open("", "_self");
                window.close();
				});
                
			}
        }
    });
	
    
});