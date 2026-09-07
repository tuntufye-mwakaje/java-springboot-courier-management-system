

$(document).ready(function () {

		
		// Alert Message
		setTimeout(() => {
			$(".msg").fadeOut();
		}, 4000);


    // Function to handle click event on collapsible items
    $('.collapsible > .nav-link').click(function (event) {
    
        event.preventDefault(); // Prevent default link behavior
        var parentCollapsible = $(this).closest('.collapsible');
        var isExpanded = parentCollapsible.hasClass('active');
  
        // Close all collapsibles at this level and below if they are currently expanded
        if (isExpanded) {
        	//page-dir
            parentCollapsible.removeClass('active');
            parentCollapsible.find('.collapsible').removeClass('active');
            parentCollapsible.find('.sub-menu').slideUp();
            
        } else {
            // Close all collapsibles at this level and below
            parentCollapsible.siblings().removeClass('active');
            parentCollapsible.siblings().find('.sub-menu').slideUp();
            // Open the clicked collapsible
            parentCollapsible.addClass('active');
            parentCollapsible.children('.sub-menu').slideDown();
        }
        
        
        
    });
  
    // Prevent collapsing when clicking on the child of a collapsible item
    $('.sub-menu .nav-link').click(function (event) {
        event.stopPropagation(); // Prevent event from bubbling up to parent collapsible
    });
  });
  
  
  
  document.addEventListener("DOMContentLoaded", function () {
    const currentUrl = window.location.href;
    const navLinks = document.querySelectorAll(".nav-link");
  
    navLinks.forEach(link => {
      const linkUrl = link.href;
      if (currentUrl === linkUrl) {
        link.classList.add("active-nav");
      }
    });
  });
  
  // // Collaspe SideBar
  
  //         document.addEventListener("DOMContentLoaded", function () {
  //             var sidebar = document.querySelector('.sidebar');
  //             var mainpage = document.querySelector('.main-content');
  //             var sidebarArrow = document.querySelector('.sidebar-arrow');
  
  //             sidebarArrow.addEventListener('click', function () {
  //                 sidebar.classList.toggle('collapsed-sidebar');
  //                 mainpage.classList.toggle('collasped-mainpage');
  //             });
  //         });
  
  
  
  
  
  document.addEventListener("DOMContentLoaded", function () {
    var sidebar = document.querySelector('.sidebar');
    var mainpage = document.querySelector('.main-content');
    var sidebarArrow = document.querySelector('.sidebar-arrow');
  
    // Function to check screen size and collapse sidebar if below 600 pixels
    function checkScreenSize() {
      if (window.innerWidth < 600) {
        sidebar.classList.add('collapsed-sidebar');
        mainpage.classList.add('collasped-mainpage');
      }
    }
  
    // Initial check when DOM is loaded
    checkScreenSize();
  
  
    // Toggle sidebar collapse on arrow click
    sidebarArrow.addEventListener('click', function () {
      $('.page-dir').addClass('ms-4');
      sidebar.classList.toggle('collapsed-sidebar');
      mainpage.classList.toggle('collasped-mainpage');
    });
  
    // Listen for window resize events and update sidebar accordingly
    window.addEventListener('resize', function () {
      checkScreenSize();
    });
  });
  
  
  //
  // current date 
  // 
  
  class CurrentDate {
    constructor() {
      this.daysOfWeek = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
      this.months = [
        'January', 'February', 'March', 'April', 'May', 'June',
        'July', 'August', 'September', 'October', 'November', 'December'
      ];
    }
  
    getFormattedDate() {
      const currentDate = new Date();
      const dayOfWeek = this.daysOfWeek[currentDate.getDay()];
      const month = this.months[currentDate.getMonth()];
      const day = currentDate.getDate();
      const year = currentDate.getFullYear();
  
      return `${dayOfWeek}, ${month} ${day}, ${year}`;
    }
  }
  
  
  const currentDateInstance = new CurrentDate();
  const formattedCurrentDate = currentDateInstance.getFormattedDate();
  
  // Update the content of the 'current-date' div
  const currentDateDiv = document.getElementById('current-date');
  currentDateDiv.textContent = formattedCurrentDate;
  
  
  
  
  
  
  
  document.addEventListener('DOMContentLoaded', function () {
    const switchBtns = document.querySelectorAll('.switch-btn');
  
    switchBtns.forEach(btn => {
        btn.addEventListener('click', function () {
            // Remove switch-active class from all buttons
            switchBtns.forEach(btn => {
                btn.classList.remove('switch-active');
            });
  
            // Add switch-active class to the clicked button
            this.classList.add('switch-active');
        });
    });
  });
  
  
  /*
              ************    Replacing Image with Input Image     ************
  */ 
  
   // Get references to the input and img elements
   const imageInputs = document.querySelectorAll('.replace-img-from');
   const previewImages = document.querySelectorAll('.replace-img-to');
   const errorMessages = document.querySelectorAll('.ImgErrorMessage');
  
   // Add event listener to each input element
   imageInputs.forEach((input, index) => {
       input.addEventListener('change', function(event) {
           const file = event.target.files[0];
           const previewImage = previewImages[index];
           const errorMessage = errorMessages[index];
  
           if (file) {
               if (file.type.startsWith('image/')) {
                   const reader = new FileReader();
  
                   reader.onload = function(e) {
                       previewImage.src = e.target.result;
                       errorMessage.style.display = 'none';
                   };
  
                   reader.readAsDataURL(file);
               } else {
                   errorMessage.style.display = 'block';
                   previewImage.src = '';
               }
           }
       });
   });
  
  // +++++++++++       Add Employee Radio Buttons      +++++++++++++
  document.addEventListener("DOMContentLoaded", function () {
    // Define the radio button groups and their corresponding div containers
    var radioGroups = [
      { name: 'add-spouse', containerId: 'add-spouse-cont' },
      { name: 'add-education', containerId: 'add-edcuation-cont' },
      { name: 'add-address', containerId: 'add-address-cont' },
      { name: 'add-children', containerId: 'add-children-cont' },
      { name: 'add-experience', containerId: 'add-experience-cont' },
      { name: 'add-document', containerId: 'add-document-cont' },
      // Add more groups here if needed
    ];
  
    // Loop through each radio button group
    radioGroups.forEach(function (group) {
      var selectedValueRadios = document.querySelectorAll('input[name="' + group.name + '"]');
      var container = document.getElementById(group.containerId);
  
      // Initial hide/show based on default selection
      toggleVisibility(selectedValueRadios, container);
  
      // Add change event listener to radio buttons
      selectedValueRadios.forEach(function (radioButton) {
        radioButton.addEventListener('change', function () {
          toggleVisibility(selectedValueRadios, container);
        });
      });
    });
  
    // Function to toggle visibility based on selected radio button
    function toggleVisibility(radios, container) {
      var selectedValue = document.querySelector('input[name="' + radios[0].name + '"]:checked').value;
      if (selectedValue === 'yes') {
        container.style.display = 'flex'; // Show div
      } else {
        container.style.display = 'none'; // Hide div
      }
    }
    
  });
  
  