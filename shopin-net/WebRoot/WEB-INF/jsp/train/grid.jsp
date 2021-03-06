<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/head.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>grid的分页</title>
</head>
<body>
<h1 style="margin:20px 0px 0px 20px;">
  Grid的分页 查询 远程排序
</h1>
<br/>
<div style="padding-left:20px;">
   <div id="panel1"></div>
    <div>事件</div>
    <textarea id="op" rows="10" style="width:800px;"></textarea> 
</div>
<script type="text/javascript">
   var app = {};
   Ext.onReady(function(){
	   Ext.QuickTips.init();
	   Ext.Msg.minWidth = 300;
	   
	   var store = new Ext.data.Store({
		   url:'${ctx}/listClass.html',
		   baseParams:{},
   		   reader:new Ext.data.JsonReader({
   			totalProperty:'results',
   			root:'rows',
   			id:'id'
   		},[
   		   {name:'id',type:'int'},
   		   {name:'nodename'},
   		   {name:'fathername'},
   		   ]
   		
   		),
   		listeners:{
   			scope:this,
   			load:function(){
   				if(grid){
   					grid.getSelectionModel().selectFirstRow();
   				}
   				
   			}
   		}
   	});   
		   
	   var grid = new Ext.grid.GridPanel({
		   enableDragDrop:true,
		   store:store,
		   ddGroup:'treeDD',
		   border:false,
		   enableDrag:true,
		   columns:[{
   			id:'id',
   			header:'编号',
   			width:80,
   			dataIndex:'id'
   		},{
   			header:'分类名',
   			width:75,
   			dataIndex:'nodename'
   		},{
   			header:'父节点名',
   			width:80,
   			dataIndex:'fathername',
   		}],
   		stripeRows:true,
		autoExpandColumn:1
	   });
	   var pageToolbar = new Ext.PagingToolbar({
		   pageSize:10,
		   displayInfo:true,
		   store:store,
		   items:["text 1"]
	   });
	   
	   var tree = new Ext.tree.TreePanel({
		   title:'类别',
		   store:store,
		   animate:false,
		   region:'west',
		   split:true,
		   width:200,
		   minSize:175,
		   maxSize:400,
		   collapsible:true,
		   hideCollapseTool:true,
		   collaspseMode:'mini',
		   margins:'0 0 5 0',
		   rootVisible:true,
		   autoScroll:true,
		   enableDD:true,
		   border:false,
		   enableDrop:true,
		   rootVisible:false,
		   root:new Ext.tree.AsyncTreeNode({
			   text:'类别',
			   id:'1',
			   expanded:true,
			   allowDrag:false,
			   allowDrop:false,
			   loader:new Ext.tree.TreeLoader({
				   dataUrl:'${ctx}/catetreeload.html',
				   clearOnLoad:true,
				   listeners:{
					  /*  scope:this,
					   load:function(loader,node,response){
						   var n = tree.getNodeById('-99');
						   if(n){
							   n.select();
						   }
					   }, */
					  /*  "beforeload":function(loader,node){
						   loader.baseParams.nodeId = node.id; 
					   } */
				   
				   }
				   
			   })
		   })
	   });
	   
	   var dataPanel = new Ext.Panel({
		   style:'padding:0px 0px 5px 0px',
		   region:'center',
		   title:'grid',
		   layout:'fit',
		   autoScroll:true,
		   closable:true,
		   tbar:pageToolbar,
		   items:grid
	   });
	   var panel = new Ext.Panel({
		   layout:'border',
		   width:800,
		   height:400,
		   applyTo:'panel1',
		   items:[tree,dataPanel]
	   });
	   store.load();
	   
   });


</script>
</body>
</html>