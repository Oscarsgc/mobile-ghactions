"use strict";(self.webpackChunkapp=self.webpackChunkapp||[]).push([[8592],{3245:(M,w,u)=>{u.d(w,{c:()=>c});var r=u(1308),v=u(7683),l=u(1911);const c=(s,n)=>{let t,e;const a=(d,f,p)=>{if("undefined"==typeof document)return;const _=document.elementFromPoint(d,f);_&&n(_)?_!==t&&(h(),i(_,p)):h()},i=(d,f)=>{t=d,e||(e=t);const p=t;(0,r.c)(()=>p.classList.add("ion-activated")),f()},h=(d=!1)=>{if(!t)return;const f=t;(0,r.c)(()=>f.classList.remove("ion-activated")),d&&e!==t&&t.click(),t=void 0};return(0,l.createGesture)({el:s,gestureName:"buttonActiveDrag",threshold:0,onStart:d=>a(d.currentX,d.currentY,v.a),onMove:d=>a(d.currentX,d.currentY,v.b),onEnd:()=>{h(!0),(0,v.h)(),e=void 0}})}},8685:(M,w,u)=>{u.d(w,{g:()=>r});const r=(n,t,e,a,i)=>l(n[1],t[1],e[1],a[1],i).map(h=>v(n[0],t[0],e[0],a[0],h)),v=(n,t,e,a,i)=>i*(3*t*Math.pow(i-1,2)+i*(-3*e*i+3*e+a*i))-n*Math.pow(i-1,3),l=(n,t,e,a,i)=>s((a-=i)-3*(e-=i)+3*(t-=i)-(n-=i),3*e-6*t+3*n,3*t-3*n,n).filter(d=>d>=0&&d<=1),s=(n,t,e,a)=>{if(0===n)return((n,t,e)=>{const a=t*t-4*n*e;return a<0?[]:[(-t+Math.sqrt(a))/(2*n),(-t-Math.sqrt(a))/(2*n)]})(t,e,a);const i=(3*(e/=n)-(t/=n)*t)/3,h=(2*t*t*t-9*t*e+27*(a/=n))/27;if(0===i)return[Math.pow(-h,1/3)];if(0===h)return[Math.sqrt(-i),-Math.sqrt(-i)];const d=Math.pow(h/2,2)+Math.pow(i/3,3);if(0===d)return[Math.pow(h/2,.5)-t/3];if(d>0)return[Math.pow(-h/2+Math.sqrt(d),1/3)-Math.pow(h/2+Math.sqrt(d),1/3)-t/3];const f=Math.sqrt(Math.pow(-i/3,3)),p=Math.acos(-h/(2*Math.sqrt(Math.pow(-i/3,3)))),_=2*Math.pow(f,1/3);return[_*Math.cos(p/3)-t/3,_*Math.cos((p+2*Math.PI)/3)-t/3,_*Math.cos((p+4*Math.PI)/3)-t/3]}},5062:(M,w,u)=>{u.d(w,{i:()=>r});const r=v=>v&&""!==v.dir?"rtl"===v.dir.toLowerCase():"rtl"===(null==document?void 0:document.dir.toLowerCase())},5106:(M,w,u)=>{u.r(w),u.d(w,{startFocusVisible:()=>c});const r="ion-focused",l=["Tab","ArrowDown","Space","Escape"," ","Shift","Enter","ArrowLeft","ArrowRight","ArrowUp","Home","End"],c=s=>{let n=[],t=!0;const e=s?s.shadowRoot:document,a=s||document.body,i=E=>{n.forEach(m=>m.classList.remove(r)),E.forEach(m=>m.classList.add(r)),n=E},h=()=>{t=!1,i([])},d=E=>{t=l.includes(E.key),t||i([])},f=E=>{if(t&&void 0!==E.composedPath){const m=E.composedPath().filter(g=>!!g.classList&&g.classList.contains("ion-focusable"));i(m)}},p=()=>{e.activeElement===a&&i([])};return e.addEventListener("keydown",d),e.addEventListener("focusin",f),e.addEventListener("focusout",p),e.addEventListener("touchstart",h),e.addEventListener("mousedown",h),{destroy:()=>{e.removeEventListener("keydown",d),e.removeEventListener("focusin",f),e.removeEventListener("focusout",p),e.removeEventListener("touchstart",h),e.removeEventListener("mousedown",h)},setFocus:i}}},4127:(M,w,u)=>{u.d(w,{C:()=>s,a:()=>l,d:()=>c});var r=u(5861),v=u(5730);const l=function(){var n=(0,r.Z)(function*(t,e,a,i,h,d){var f;if(t)return t.attachViewToDom(e,a,h,i);if(!(d||"string"==typeof a||a instanceof HTMLElement))throw new Error("framework delegate is missing");const p="string"==typeof a?null===(f=e.ownerDocument)||void 0===f?void 0:f.createElement(a):a;return i&&i.forEach(_=>p.classList.add(_)),h&&Object.assign(p,h),e.appendChild(p),yield new Promise(_=>(0,v.c)(p,_)),p});return function(e,a,i,h,d,f){return n.apply(this,arguments)}}(),c=(n,t)=>{if(t){if(n)return n.removeViewFromDom(t.parentElement,t);t.remove()}return Promise.resolve()},s=()=>{let n,t;return{attachViewToDom:function(){var i=(0,r.Z)(function*(h,d,f={},p=[]){var _,E;if(n=h,d){const g="string"==typeof d?null===(_=n.ownerDocument)||void 0===_?void 0:_.createElement(d):d;p.forEach(o=>g.classList.add(o)),Object.assign(g,f),n.appendChild(g),yield new Promise(o=>(0,v.c)(g,o))}else if(n.children.length>0){const g=null===(E=n.ownerDocument)||void 0===E?void 0:E.createElement("div");p.forEach(o=>g.classList.add(o)),g.append(...n.children),n.appendChild(g)}const m=document.querySelector("ion-app")||document.body;return t=document.createComment("ionic teleport"),n.parentNode.insertBefore(t,n),m.appendChild(n),n});return function(d,f){return i.apply(this,arguments)}}(),removeViewFromDom:()=>(n&&t&&(t.parentNode.insertBefore(n,t),t.remove()),Promise.resolve())}}},7683:(M,w,u)=>{u.d(w,{a:()=>l,b:()=>c,c:()=>v,d:()=>n,h:()=>s});const r={getEngine(){var t;const e=window;return e.TapticEngine||(null===(t=e.Capacitor)||void 0===t?void 0:t.isPluginAvailable("Haptics"))&&e.Capacitor.Plugins.Haptics},available(){return!!this.getEngine()},isCordova:()=>!!window.TapticEngine,isCapacitor:()=>!!window.Capacitor,impact(t){const e=this.getEngine();if(!e)return;const a=this.isCapacitor()?t.style.toUpperCase():t.style;e.impact({style:a})},notification(t){const e=this.getEngine();if(!e)return;const a=this.isCapacitor()?t.style.toUpperCase():t.style;e.notification({style:a})},selection(){this.impact({style:"light"})},selectionStart(){const t=this.getEngine();!t||(this.isCapacitor()?t.selectionStart():t.gestureSelectionStart())},selectionChanged(){const t=this.getEngine();!t||(this.isCapacitor()?t.selectionChanged():t.gestureSelectionChanged())},selectionEnd(){const t=this.getEngine();!t||(this.isCapacitor()?t.selectionEnd():t.gestureSelectionEnd())}},v=()=>{r.selection()},l=()=>{r.selectionStart()},c=()=>{r.selectionChanged()},s=()=>{r.selectionEnd()},n=t=>{r.impact(t)}},3938:(M,w,u)=>{u.d(w,{a:()=>r,b:()=>d,c:()=>t,d:()=>f,e:()=>D,f:()=>n,g:()=>p,h:()=>l,i:()=>v,j:()=>o,k:()=>y,l:()=>e,m:()=>i,n:()=>_,o:()=>a,p:()=>s,q:()=>c,r:()=>g,s:()=>C,t:()=>h,u:()=>E,v:()=>m});const r="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' class='ionicon' viewBox='0 0 512 512'><title>Arrow Back</title><path stroke-linecap='square' stroke-miterlimit='10' stroke-width='48' d='M244 400L100 256l144-144M120 256h292' class='ionicon-fill-none'/></svg>",v="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' class='ionicon' viewBox='0 0 512 512'><title>Arrow Down</title><path stroke-linecap='round' stroke-linejoin='round' stroke-width='48' d='M112 268l144 144 144-144M256 392V100' class='ionicon-fill-none'/></svg>",l="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' class='ionicon' viewBox='0 0 512 512'><title>Caret Back</title><path d='M368 64L144 256l224 192V64z'/></svg>",c="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' class='ionicon' viewBox='0 0 512 512'><title>Caret Down</title><path d='M64 144l192 224 192-224H64z'/></svg>",s="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' class='ionicon' viewBox='0 0 512 512'><title>Caret Up</title><path d='M448 368L256 144 64 368h384z'/></svg>",n="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' class='ionicon' viewBox='0 0 512 512'><title>Checkmark</title><path stroke-linecap='round' stroke-linejoin='round' d='M416 128L192 384l-96-96' class='ionicon-fill-none ionicon-stroke-width'/></svg>",t="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' class='ionicon' viewBox='0 0 512 512'><title>Chevron Back</title><path stroke-linecap='round' stroke-linejoin='round' stroke-width='48' d='M328 112L184 256l144 144' class='ionicon-fill-none'/></svg>",e="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' class='ionicon' viewBox='0 0 512 512'><title>Chevron Down</title><path stroke-linecap='round' stroke-linejoin='round' stroke-width='48' d='M112 184l144 144 144-144' class='ionicon-fill-none'/></svg>",a="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' class='ionicon' viewBox='0 0 512 512'><title>Chevron Forward</title><path stroke-linecap='round' stroke-linejoin='round' stroke-width='48' d='M184 112l144 144-144 144' class='ionicon-fill-none'/></svg>",i="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' class='ionicon' viewBox='0 0 512 512'><title>Chevron Forward</title><path stroke-linecap='round' stroke-linejoin='round' stroke-width='48' d='M184 112l144 144-144 144' class='ionicon-fill-none'/></svg>",h="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' class='ionicon' viewBox='0 0 512 512'><title>Close</title><path d='M289.94 256l95-95A24 24 0 00351 127l-95 95-95-95a24 24 0 00-34 34l95 95-95 95a24 24 0 1034 34l95-95 95 95a24 24 0 0034-34z'/></svg>",d="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' class='ionicon' viewBox='0 0 512 512'><title>Close Circle</title><path d='M256 48C141.31 48 48 141.31 48 256s93.31 208 208 208 208-93.31 208-208S370.69 48 256 48zm75.31 260.69a16 16 0 11-22.62 22.62L256 278.63l-52.69 52.68a16 16 0 01-22.62-22.62L233.37 256l-52.68-52.69a16 16 0 0122.62-22.62L256 233.37l52.69-52.68a16 16 0 0122.62 22.62L278.63 256z'/></svg>",f="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' class='ionicon' viewBox='0 0 512 512'><title>Close</title><path d='M400 145.49L366.51 112 256 222.51 145.49 112 112 145.49 222.51 256 112 366.51 145.49 400 256 289.49 366.51 400 400 366.51 289.49 256 400 145.49z'/></svg>",p="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' class='ionicon' viewBox='0 0 512 512'><title>Ellipse</title><circle cx='256' cy='256' r='192' stroke-linecap='round' stroke-linejoin='round' class='ionicon-fill-none ionicon-stroke-width'/></svg>",_="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' class='ionicon' viewBox='0 0 512 512'><title>Ellipsis Horizontal</title><circle cx='256' cy='256' r='48'/><circle cx='416' cy='256' r='48'/><circle cx='96' cy='256' r='48'/></svg>",E="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' class='ionicon' viewBox='0 0 512 512'><title>Menu</title><path stroke-linecap='round' stroke-miterlimit='10' d='M80 160h352M80 256h352M80 352h352' class='ionicon-fill-none ionicon-stroke-width'/></svg>",m="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' class='ionicon' viewBox='0 0 512 512'><title>Menu</title><path d='M64 384h384v-42.67H64zm0-106.67h384v-42.66H64zM64 128v42.67h384V128z'/></svg>",g="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' class='ionicon' viewBox='0 0 512 512'><title>Remove</title><path stroke-linecap='round' stroke-linejoin='round' d='M400 256H112' class='ionicon-fill-none ionicon-stroke-width'/></svg>",o="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' class='ionicon' viewBox='0 0 512 512'><title>Reorder Three</title><path stroke-linecap='round' stroke-linejoin='round' d='M96 256h320M96 176h320M96 336h320' class='ionicon-fill-none ionicon-stroke-width'/></svg>",y="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' class='ionicon' viewBox='0 0 512 512'><title>Reorder Two</title><path stroke-linecap='square' stroke-linejoin='round' stroke-width='44' d='M118 304h276M118 208h276' class='ionicon-fill-none'/></svg>",C="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' class='ionicon' viewBox='0 0 512 512'><title>Search</title><path d='M221.09 64a157.09 157.09 0 10157.09 157.09A157.1 157.1 0 00221.09 64z' stroke-miterlimit='10' class='ionicon-fill-none ionicon-stroke-width'/><path stroke-linecap='round' stroke-miterlimit='10' d='M338.29 338.29L448 448' class='ionicon-fill-none ionicon-stroke-width'/></svg>",D="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' class='ionicon' viewBox='0 0 512 512'><title>Search</title><path d='M464 428L339.92 303.9a160.48 160.48 0 0030.72-94.58C370.64 120.37 298.27 48 209.32 48S48 120.37 48 209.32s72.37 161.32 161.32 161.32a160.48 160.48 0 0094.58-30.72L428 464zM209.32 319.69a110.38 110.38 0 11110.37-110.37 110.5 110.5 0 01-110.37 110.37z'/></svg>"},6642:(M,w,u)=>{u.d(w,{I:()=>s,a:()=>i,b:()=>n,c:()=>f,d:()=>_,f:()=>h,g:()=>a,i:()=>e,p:()=>p,r:()=>E,s:()=>d});var r=u(5861),v=u(5730),l=u(4147);const s="ion-content",n=".ion-content-scroll-host",t=`${s}, ${n}`,e=m=>"ION-CONTENT"===m.tagName,a=function(){var m=(0,r.Z)(function*(g){return e(g)?(yield new Promise(o=>(0,v.c)(g,o)),g.getScrollElement()):g});return function(o){return m.apply(this,arguments)}}(),i=m=>m.querySelector(n)||m.querySelector(t),h=m=>m.closest(t),d=(m,g)=>e(m)?m.scrollToTop(g):Promise.resolve(m.scrollTo({top:0,left:0,behavior:g>0?"smooth":"auto"})),f=(m,g,o,y)=>e(m)?m.scrollByPoint(g,o,y):Promise.resolve(m.scrollBy({top:o,left:g,behavior:y>0?"smooth":"auto"})),p=m=>(0,l.a)(m,s),_=m=>{if(e(m)){const o=m.scrollY;return m.scrollY=!1,o}return m.style.setProperty("overflow","hidden"),!0},E=(m,g)=>{e(m)?m.scrollY=g:m.style.removeProperty("overflow")}},8439:(M,w,u)=>{u.d(w,{s:()=>r});const r=e=>{try{if(e instanceof class t{constructor(a){this.value=a}})return e.value;if(!c()||"string"!=typeof e||""===e)return e;const a=document.createDocumentFragment(),i=document.createElement("div");a.appendChild(i),i.innerHTML=e,n.forEach(p=>{const _=a.querySelectorAll(p);for(let E=_.length-1;E>=0;E--){const m=_[E];m.parentNode?m.parentNode.removeChild(m):a.removeChild(m);const g=l(m);for(let o=0;o<g.length;o++)v(g[o])}});const h=l(a);for(let p=0;p<h.length;p++)v(h[p]);const d=document.createElement("div");d.appendChild(a);const f=d.querySelector("div");return null!==f?f.innerHTML:d.innerHTML}catch(a){return console.error(a),""}},v=e=>{if(e.nodeType&&1!==e.nodeType)return;for(let i=e.attributes.length-1;i>=0;i--){const h=e.attributes.item(i),d=h.name;if(!s.includes(d.toLowerCase())){e.removeAttribute(d);continue}const f=h.value;null!=f&&f.toLowerCase().includes("javascript:")&&e.removeAttribute(d)}const a=l(e);for(let i=0;i<a.length;i++)v(a[i])},l=e=>null!=e.children?e.children:e.childNodes,c=()=>{var e;const a=window,i=null===(e=null==a?void 0:a.Ionic)||void 0===e?void 0:e.config;return!i||(i.get?i.get("sanitizerEnabled",!0):!0===i.sanitizerEnabled||void 0===i.sanitizerEnabled)},s=["class","id","href","src","name","slot"],n=["script","style","iframe","meta","link","object","embed"]},1316:(M,w,u)=>{u.r(w),u.d(w,{KEYBOARD_DID_CLOSE:()=>v,KEYBOARD_DID_OPEN:()=>r,copyVisualViewport:()=>g,keyboardDidClose:()=>p,keyboardDidOpen:()=>d,keyboardDidResize:()=>f,resetKeyboardAssist:()=>t,setKeyboardClose:()=>h,setKeyboardOpen:()=>i,startKeyboardAssist:()=>e,trackViewportChanges:()=>m});const r="ionKeyboardDidShow",v="ionKeyboardDidHide";let c={},s={},n=!1;const t=()=>{c={},s={},n=!1},e=o=>{a(o),o.visualViewport&&(s=g(o.visualViewport),o.visualViewport.onresize=()=>{m(o),d()||f(o)?i(o):p(o)&&h(o)})},a=o=>{o.addEventListener("keyboardDidShow",y=>i(o,y)),o.addEventListener("keyboardDidHide",()=>h(o))},i=(o,y)=>{_(o,y),n=!0},h=o=>{E(o),n=!1},d=()=>!n&&c.width===s.width&&(c.height-s.height)*s.scale>150,f=o=>n&&!p(o),p=o=>n&&s.height===o.innerHeight,_=(o,y)=>{const D=new CustomEvent(r,{detail:{keyboardHeight:y?y.keyboardHeight:o.innerHeight-s.height}});o.dispatchEvent(D)},E=o=>{const y=new CustomEvent(v);o.dispatchEvent(y)},m=o=>{c=Object.assign({},s),s=g(o.visualViewport)},g=o=>({width:Math.round(o.width),height:Math.round(o.height),offsetTop:o.offsetTop,offsetLeft:o.offsetLeft,pageTop:o.pageTop,pageLeft:o.pageLeft,scale:o.scale})},9852:(M,w,u)=>{u.d(w,{c:()=>v});var r=u(3457);const v=l=>{let c,s,n;const t=()=>{c=()=>{n=!0,l&&l(!0)},s=()=>{n=!1,l&&l(!1)},null==r.w||r.w.addEventListener("keyboardWillShow",c),null==r.w||r.w.addEventListener("keyboardWillHide",s)};return t(),{init:t,destroy:()=>{null==r.w||r.w.removeEventListener("keyboardWillShow",c),null==r.w||r.w.removeEventListener("keyboardWillHide",s),c=s=void 0},isKeyboardVisible:()=>n}}},7741:(M,w,u)=>{u.d(w,{S:()=>v});const v={bubbles:{dur:1e3,circles:9,fn:(l,c,s)=>{const n=l*c/s-l+"ms",t=2*Math.PI*c/s;return{r:5,style:{top:9*Math.sin(t)+"px",left:9*Math.cos(t)+"px","animation-delay":n}}}},circles:{dur:1e3,circles:8,fn:(l,c,s)=>{const n=c/s,t=l*n-l+"ms",e=2*Math.PI*n;return{r:5,style:{top:9*Math.sin(e)+"px",left:9*Math.cos(e)+"px","animation-delay":t}}}},circular:{dur:1400,elmDuration:!0,circles:1,fn:()=>({r:20,cx:48,cy:48,fill:"none",viewBox:"24 24 48 48",transform:"translate(0,0)",style:{}})},crescent:{dur:750,circles:1,fn:()=>({r:26,style:{}})},dots:{dur:750,circles:3,fn:(l,c)=>({r:6,style:{left:9-9*c+"px","animation-delay":-110*c+"ms"}})},lines:{dur:1e3,lines:8,fn:(l,c,s)=>({y1:14,y2:26,style:{transform:`rotate(${360/s*c+(c<s/2?180:-180)}deg)`,"animation-delay":l*c/s-l+"ms"}})},"lines-small":{dur:1e3,lines:8,fn:(l,c,s)=>({y1:12,y2:20,style:{transform:`rotate(${360/s*c+(c<s/2?180:-180)}deg)`,"animation-delay":l*c/s-l+"ms"}})},"lines-sharp":{dur:1e3,lines:12,fn:(l,c,s)=>({y1:17,y2:29,style:{transform:`rotate(${30*c+(c<6?180:-180)}deg)`,"animation-delay":l*c/s-l+"ms"}})},"lines-sharp-small":{dur:1e3,lines:12,fn:(l,c,s)=>({y1:12,y2:20,style:{transform:`rotate(${30*c+(c<6?180:-180)}deg)`,"animation-delay":l*c/s-l+"ms"}})}}},4464:(M,w,u)=>{u.r(w),u.d(w,{createSwipeBackGesture:()=>s});var r=u(5730),v=u(5062),l=u(1911);u(4349);const s=(n,t,e,a,i)=>{const h=n.ownerDocument.defaultView,d=(0,v.i)(n),p=o=>d?-o.deltaX:o.deltaX;return(0,l.createGesture)({el:n,gestureName:"goback-swipe",gesturePriority:40,threshold:10,canStart:o=>(o=>{const{startX:C}=o;return d?C>=h.innerWidth-50:C<=50})(o)&&t(),onStart:e,onMove:o=>{const C=p(o)/h.innerWidth;a(C)},onEnd:o=>{const y=p(o),C=h.innerWidth,D=y/C,x=(o=>d?-o.velocityX:o.velocityX)(o),O=x>=0&&(x>.2||y>C/2),L=(O?1-D:D)*C;let k=0;if(L>5){const A=L/Math.abs(x);k=Math.min(A,540)}i(O,D<=0?.01:(0,r.l)(0,D,.9999),k)}})}},4762:(M,w,u)=>{u.d(w,{Z:()=>v});var r=u(2096);let v=(()=>{class l{constructor(){}ngOnInit(){}}return l.\u0275fac=function(s){return new(s||l)},l.\u0275cmp=r.Xpm({type:l,selectors:[["app-explore-container"]],inputs:{name:"name"},decls:9,vars:1,consts:[["id","container"],["target","_blank","rel","noopener noreferrer","href","https://ionicframework.com/docs/components"]],template:function(s,n){1&s&&(r.TgZ(0,"div",0)(1,"strong"),r._uU(2),r.qZA(),r.TgZ(3,"p"),r._uU(4,"ALOHAAAAAAAAAAAAA"),r.qZA(),r.TgZ(5,"p"),r._uU(6,"Explore "),r.TgZ(7,"a",1),r._uU(8,"UI Components"),r.qZA()()()),2&s&&(r.xp6(2),r.Oqu(n.name))},styles:["#container[_ngcontent-%COMP%]{text-align:center;position:absolute;left:0;right:0;top:50%;transform:translateY(-50%)}#container[_ngcontent-%COMP%]   strong[_ngcontent-%COMP%]{font-size:20px;line-height:26px}#container[_ngcontent-%COMP%]   p[_ngcontent-%COMP%]{font-size:16px;line-height:22px;color:#8c8c8c;margin:0}#container[_ngcontent-%COMP%]   a[_ngcontent-%COMP%]{text-decoration:none}"]}),l})()},581:(M,w,u)=>{u.d(w,{e:()=>s});var r=u(9808),v=u(4182),l=u(9250),c=u(2096);let s=(()=>{class n{}return n.\u0275fac=function(e){return new(e||n)},n.\u0275mod=c.oAB({type:n}),n.\u0275inj=c.cJS({imports:[r.ez,v.u5,l.Pc]}),n})()}}]);