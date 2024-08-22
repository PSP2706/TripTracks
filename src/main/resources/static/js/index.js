function scrollToSection(sectionId) {
    const element = document.getElementById(sectionId);
    if (element) {
        element.scrollIntoView({ behavior: 'smooth' });
    }
}
function scrollToSectionSidebar(sectionId) {
    const sidebar = document.querySelector('.sidebar');
    const element = document.getElementById(sectionId);
    if (element) {
        sidebar.style.display = 'none';
        element.scrollIntoView({ behavior: 'smooth' }); 
    }
    const arrowUp=document.querySelector('.move-up');
    if(arrowUp){
        arrowUp.style.display='flex';
    }
}

let logIn=document.querySelector('.login-btn');
let signUp=document.querySelector('.signup-btn');

logIn.addEventListener('click', function() {
    window.open('login.html', '_self');
});

signUp.addEventListener('click', function() {
    window.open('signup.html', '_self');
});

let slideIndex = 0;
const slides = document.querySelector('.slides');
const images = slides.querySelectorAll('img');
const totalSlides = images.length;

function moveSlide(direction) {
  slideIndex += direction;
  
  if (slideIndex === totalSlides) {
    slideIndex = 0;
  } else if (slideIndex < 0) {
    slideIndex = totalSlides - 1;
  }
  
  slides.style.transform = `translateX(${-slideIndex * 100}%)`;
}

function hideSidebar() {
    const sidebar = document.querySelector('.sidebar');
    if (sidebar) {
        sidebar.style.display = 'none';
    }
    const arrowUp=document.querySelector('.move-up');
    if(arrowUp){
        arrowUp.style.display='flex';
    }
}

function showSideBar(){
    const sidebar=document.querySelector('.sidebar');
    if(sidebar){
        sidebar.style.display='flex';
    }
    const arrowUp=document.querySelector('.move-up');
    if(arrowUp){
        arrowUp.style.display='none';
    }
}

function moveToHeader() {
    const element = document.querySelector('.navbar');
    if (element) {
        element.scrollIntoView({ behavior: 'smooth' });
    }
}
