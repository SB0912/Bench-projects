gsap.to(
    ".right-door",
    {
      opacity: 0.85,
      scrollTrigger: {
        trigger: ".right-door",
        scrub: true,
        start: "top top"
      },
      xPercent: -80
    }
  );

  gsap.to(
    ".left-door",
    {
      opacity: 0.85,
      scrollTrigger: {
        trigger: ".left-door",
        scrub: true,
        start: "top top"
      },
      xPercent: 80
    }
  );

    gsap.to(".hello", {
        scrollTrigger: {
            trigger: ".hello",
            pin: ".hello",
            start: "top top",
            end: "+=2800"
        },
    });

    gsap.to(".note", {
      scrollTrigger: {
          trigger: ".note",
          pin: ".note",
          start: "top top",
          end: "+=2800"
      },
  });

  gsap.to(".conclusion", {
    scrollTrigger: {
        trigger: ".conclusion",
        pin: ".conclusion",
        start: "top top",
        end: "+=2700"
    },
});

const sections = document.querySelectorAll('section')
const hellos = document.querySelectorAll('.hello')
const notes = document.querySelectorAll('.note')
const conclusions = document.querySelectorAll('.conslusion')

hellos.forEach((hello, i) => {
    gsap.fromTo(hello, 
       {opacity: 0,
        filter: "blur(10px)"},
       {
        opacity: 1,
        filter: "blur(0px)",
        ease: 'power1.inOut',
        scrollTrigger: {
            trigger: sections[i],
            pin: 'main',
          pinnedContainer: "main",
            start: 'top center',
            end: `+=${i + 3}00%`,
            scrub: true,
        },
    })
})

gsap.fromTo(".reveal", { scale: 1 }, { scale: 1.1,
    scrollTrigger: {
        trigger: ".reveal",
        pin: ".reveal",
        start: "center center",
        end: "bottom -100%",
        scrub: true
    },
});


const pic = document.getElementById('slide');

const sources = [
  'media/IMG_4325.jpg',
  'media/IMG_4326.jpg',
  'media/IMG_4327.jpg',
  'media/IMG_4328.jpg',
  'media/IMG_4329.jpg',
  'media/IMG_4331.jpg',
  'media/IMG_4332.jpg'
];

let sourceIndex = 0;

function changePicSource() {
  pic.src = sources[sourceIndex];
  sourceIndex++;
  if (sourceIndex >= sources.length) {
    sourceIndex = 0;
  }
}

setInterval(changePicSource, 10 * 500)