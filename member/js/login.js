function slideFuns(){
    var btnDomStr=['btnSignUp1','btnSignIn','btnSignUp','btnReset','signOut','signIn'];
    var btnDomArr=[];
    var Int1=null,Int2=null;
    for(var i=0;i<btnDomStr.length;i++){
      btnDomArr[i]=getDom(btnDomStr[i]);
    }
    btnDomArr[0].onclick=function(){
        var upY1=0,upY2=90;
        Int1=setInterval(slide1(upY1,upY2,btnDomArr[4],btnDomArr[5],Int1),10);
      }
    btnDomArr[3].onclick=function(){
        var upY1=90,upY2=0;
        Int2=setInterval(slide2(upY1,upY2,btnDomArr[4],btnDomArr[5],Int2),10);
      }
    btnDomArr[1].onclick=function(){
      var strArr=["outUserName","outPwd"];
      isSignOut(strArr,btnDomArr[4],"signOut");
    }
    btnDomArr[2].onclick=function(){
      var strArr=["inUserName","inPwd1","inPwd2","yzm"];
      isSignOut(strArr,btnDomArr[5],"signIn");
    }
  }
  function slide1(y1,y2,that1,that2,Int){
    return function(){
        if(y1<90){
          that1.style.left=(-y1)+"%";
          y1+=5;
        }if(y2>-15){
          that2.style.right=(-y2)+"%";
          y2-=5;
        }else{
          clearInterval(Int);
        }
    }
  }
  function slide2(y1,y2,that1,that2,Int){
    return function(){
        if(y1>-15){
          that1.style.left=(-y1)+"%";
          y1-=5;
        }if(y2<90){
          that2.style.right=(-y2)+"%";
          y2+=5;
        }else{
          clearInterval(Int);
        }
    }
  }
  //定义DOM函数
  function getDom(dom){
    var domNode=document.getElementById(dom)?document.getElementById(dom):document.getElementsByClassName(dom)[0];
    return domNode;
  }
  //登陆数据判断
  function isSignOut(parmArr,parentDom,classStr){
    var strArr=parmArr;
    var domArr=[];
    for(var i=0;i<strArr.length;i++){
      domArr[i]=getDom(strArr[i]);
    }
    if(domArr.length != 0){
      for(var j=0;j<domArr.length;j++){
        var domVal=domArr[j].value;
        if(domVal==""){
          parentDom.setAttribute("class",classStr+" error");
          domArr[j].style.border="1px solid #f00";
        }
        domArr[j].onfocus=function(){
          this.style.border="1px solid #ccc";
          parentDom.setAttribute("class",classStr);
        }
      }
    }
  }
  window.onload=function(){
      slideFuns();
  }