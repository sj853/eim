//查询的关键字
var key;
//关键字的值
var val;



var typeVal;

$(function(){
    var lastIndex;
    var key="all";
	var val="";
    //表格设置---------------------------------------------
    var grid = $('#data').datagrid({
        pagination: true,
        rownumbers: true,
        loadMsg: '数据加载中了.....',
        queryParams: {type:"search",key:key,val:val},
        url: "servlet/DeptServlet",
        toolbar: [{
            text: '添加记录',
            iconCls: 'icon-add',
            handler: function(){
                $("#detail input").each(function(){
					$(this).attr("value","");
                    $(this).removeAttr("disabled");
                });
		    win.window('open');
			typeVal = "add";
							 
            }
        }, '-', {
            text: '删除记录',
            iconCls: 'icon-remove',
            handler: function(){
			var row = $("#data").datagrid("getSelected");
			if(row){
			for(var prop in row){
				$("#detail input[name='"+prop+"']").attr("value",row[prop]);
			}
			$.messager.confirm("警告！","您确定要永久删除此条数据吗?",function(data){
				if(data){
                  typeVal = "del";
					getData();
				}
			});
			}
			else{
				$.messager.alert("警告！","请先选择一行!");
			}
			
            }
        }, '-', {
            text: '提交',
            iconCls: 'icon-save',
            handler: function(){
                $('#data').datagrid('acceptChanges');
            }
        }, '-', {
            text: '撤销',
            iconCls: 'icon-undo',
            handler: function(){
                $('#data').datagrid('rejectChanges');
            }
        }, '-', {
            text: '更新条数',
            iconCls: 'icon-search',
            handler: function(){
                var rows = $('#data').datagrid('getChanges');
                alert('changed rows: ' + rows.length + ' lines');
            }
        }],
        onBeforeLoad: function(){
        
            $(this).datagrid('rejectChanges');
        },
        onLoadError: function(){
            alert("获取信息失败!");
        },
        
        onClickRow: function(rowIndex){
            if (lastIndex != rowIndex) {
                $('#data').datagrid('endEdit', lastIndex);
                $('#data').datagrid('beginEdit', rowIndex);
            }
            lastIndex = rowIndex;
        },
        //双击事件
        onDblClickRow: function(rowIndex){
			win.window('open');
			 typeVal = "update";
			var row = $("#data").datagrid("getSelected");
			for(var prop in row){
				$("#detail input[name='"+prop+"']").attr("value",row[prop]);
			}
        }
    });
    
	//------------------------------------------------------
	
//对话框设置	------------------------------------------------------------
            $('#dd').show().dialog({
                title: '详细信息',
                iconCls: 'icon-ok',
                toolbar: [{
                    text: '修改',
                    iconCls: 'icon-edit',
                    handler: function(){
                        $("#detail input").each(function(){
                            $(this).removeAttr("disabled");
                        });
                    }
                }, '-', {
                    text: '保存',
                    iconCls: 'icon-save',
                    handler: function(){
						getData();
                    }
                }],
                buttons: [{
                    text: '关闭',
                    iconCls: 'icon-ok',
                    handler: function(){
                        $('#dd').dialog('close');
						 $("#detail input[name]").each(function(){
									$(this).attr("disabled","true");
								});
                    }
                }]
            });
    //--------------------------------------------------------------------------
    
	function getData(){
			var idVal = $("input[name='id']").attr("value");
					var nameVal = $("input[name='name']").attr("value");
					var superidVal = $("input[name='superid']").attr("value");
					
						$.post("servlet/DeptServlet",{
							type:typeVal,
							id:idVal,
							name:nameVal,
							superid:superidVal
						},function(data){
							if(data){
                                $("input[name]").each(function(){
									$(this).attr("disabled","true");
								});
							 $('#data').datagrid('reload');
							}
							else{
								$.messager.alert("错误","操作或数据传输出错，请稍后再试...");
							}
							
						});
	}
	
	
	//弹出框口设置
	var win = $('#dd').window({
		closed:true
	});
	
	
});
