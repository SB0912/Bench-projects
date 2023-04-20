gsap.to(
    ".right-door",
    {
      opacity: 0.85,
      scrollTrigger: {
        trigger: ".right-door",
        scrub: true,
        start: "top top"
      },
      xPercent: -90
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
      xPercent: 90
    }
  );

    gsap.to(".hello", {
        scrollTrigger: {
            trigger: ".hello",
            pin: ".hello",
            start: "top top",
            end: "+=2100"
        },
    });

const sections = document.querySelectorAll('section')
const hellos = document.querySelectorAll('.hello')

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

gsap.fromTo(".reveal", { scale: 1 }, { scale: 1.2,
    scrollTrigger: {
        trigger: ".reveal",
        pin: ".reveal",
        start: "top top",
        end: "bottom -100%",
        scrub: true
    },
});