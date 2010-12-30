//查询的关键字
var key;
//关键字的值
var val;

$(function(){
    var lastIndex;
    var key="all";
	var val="";
    //表格设置---------------------------------------------
    var grid = $('#tt').datagrid({
        pagination: true,
        rownumbers: true,
        loadMsg: '数据加载中了.....',
        queryParams: {type:"search",key:key,val:val},
        url: "servlet/DeptServlet",
        toolbar: [{
            text: '添加记录',
            iconCls: 'icon-add',
            handler: function(){
                $('#tt').datagrid('endEdit', lastIndex);
                $('#tt').datagrid('appendRow', {
                    itemid: '',
                    productid: '',
                    listprice: '',
                    unitprice: '',
                    attr1: '',
                    status: 'P'
                });
                var lastIndex = $('#tt').datagrid('getRows').length - 1;
                $('#tt').datagrid('beginEdit', lastIndex);
            }
        }, '-', {
            text: '删除记录',
            iconCls: 'icon-remove',
            handler: function(){
                var row = $('#tt').datagrid('getSelected');
                if (row) {
                    var index = $('#tt').datagrid('getRowIndex', row);
                    $('#tt').datagrid('deleteRow', index);
                }
            }
        }, '-', {
            text: '提交',
            iconCls: 'icon-save',
            handler: function(){
                $('#tt').datagrid('acceptChanges');
            }
        }, '-', {
            text: '撤销',
            iconCls: 'icon-undo',
            handler: function(){
                $('#tt').datagrid('rejectChanges');
            }
        }, '-', {
            text: '更新条数',
            iconCls: 'icon-search',
            handler: function(){
                var rows = $('#tt').datagrid('getChanges');
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
                $('#tt').datagrid('endEdit', lastIndex);
                $('#tt').datagrid('beginEdit', rowIndex);
            }
            lastIndex = rowIndex;
        },
        //双击事件
        onDblClickRow: function(rowIndex){
			win.window('open');
			var row = $("#tt").datagrid("getSelected");
			var num=0;
			for(var prop in row){
				$("#detail input[name='"+prop+"']").attr("value",row[prop]);
				num++;
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
						var idVal = $("input[name='id']").attr("value");
						var nameVal = $("input[name='name']").attr("value");
						var superidVal = $("input[name='superid']").attr("value");
						$.post("servlet/DeptServlet",{
							type:"update",
							id:idVal,
							name:nameVal,
							superid:superidVal
						},function(data){
							if(data){
                                $("input[name]").each(function(){
									$(this).attr("disabled","true");
								});
							 $('#tt').datagrid('reload');
//                                $('#dd').window({
//                                    closed: true
//                                });
							}
							else{
								alert("修改失败！请稍后再试...");
							}
							
						});
                    }
                }],
                buttons: [{
                    text: '关闭',
                    iconCls: 'icon-ok',
                    handler: function(){
                        $('#dd').dialog('close');
                    }
                }]
            });
    //--------------------------------------------------------------------------
    
	var win = $('#dd').window({
		closed:true
	});
	
	
});
