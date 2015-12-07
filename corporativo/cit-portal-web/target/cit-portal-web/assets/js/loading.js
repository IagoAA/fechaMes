var cSpeed=9;
var cWidth=35;
var cHeight=35;
var cTotalFrames=12;
var cFrameWidth=35;
var cImageSrc='/cit-portal-web/assets/css/images/loading-sprite.png';

var cImageTimeout=false;
var cIndex=0;
var cXpos=0;
var cPreloaderTimeout=false;
var SECONDS_BETWEEN_FRAMES=0;
var target = document.getElementById('loading-feed-image');

function startAnimation() {
	
	target.style.backgroundImage='url('+cImageSrc+')';
	target.style.width=cWidth+'px';
	target.style.height=cHeight+'px';
	
	FPS = Math.round(100/cSpeed);
	SECONDS_BETWEEN_FRAMES = 1 / FPS;
	
	cPreloaderTimeout=setTimeout('continueAnimation()', SECONDS_BETWEEN_FRAMES/1000);
	
}

function continueAnimation() {
	
	cXpos += cFrameWidth;
	cIndex += 1;
	 
	if (cIndex >= cTotalFrames) {
		cXpos =0;
		cIndex=0;
	}
	
	if(target)
		target.style.backgroundPosition=(-cXpos)+'px 0';
	
	cPreloaderTimeout=setTimeout('continueAnimation()', SECONDS_BETWEEN_FRAMES*1000);
}

function stopAnimation() {
	clearTimeout(cPreloaderTimeout);
	cPreloaderTimeout=false;
}

function imageLoader(s, fun) {
	clearTimeout(cImageTimeout);
	cImageTimeout=0;
	genImage = new Image();
	genImage.onload=function (){cImageTimeout=setTimeout(fun, 0)};
	genImage.onerror=new Function('alert(\'Could not load the image\')');
	genImage.src=s;
}

new imageLoader(cImageSrc, 'startAnimation()');