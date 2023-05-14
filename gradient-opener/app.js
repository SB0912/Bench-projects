    // gsap.fromTo(".container", {opacity: 1}, { opacity: 0.75,
    //     scrollTrigger: {
    //         trigger: ".open",
    //         scrub: true,
    //         pin: ".container",
    //         start : "top top",
    //         end : "bottom -100%",
    //     },
    //     x: -1300
    // });

    // gsap.fromTo(".container", {opacity: 1}, { opacity: 0.75,
    //     scrollTrigger: {
    //         trigger: ".open",
    //         scrub: true,
    //         pin: ".container",
    //         start : "top top",
    //         end : "bottom -100%"
    //     },
    //     x: 1000
    // });

    // gsap.fromTo(".container", { filter: "blur(10px)", scale: 1  }, { filter: "blur(0px)", scale: 1.1 ,
    //     scrollTrigger: {
    //         trigger: ".container",
    //         pin: ".container",
    //         start: "top top",
    //         end: "bottom -100%",
    //         scrub: true
    //     },
    // });

    // gsap.fromTo(".container", { filter: "blur(10px)", scale: 1 }, { filter: "blur(0px)", scale: 1.03 ,
    //     scrollTrigger: {
    //         trigger: ".container",
    //         pin: ".container",
    //         start: "center center",
    //         end: "bottom -100%",
    //         scrub: true
    //     },
    // });

gsap
  .timeline({
    scrollTrigger: {
      trigger: ".container",
      pin: true,
      start: "top top",
      end: "+=100%",
      scrub: true
    }
  })
  .fromTo(
    ".box1",
    { opacity: 1 },
    {
      opacity: 0.55,
      x: -2700,
    }
  )
  .fromTo(
    ".box2",
    { opacity: 1 },
    {
      opacity: 0.55,
      x: 2000,
    },
    0
  )
  .fromTo(
    ".reveal",
    { filter: "blur(10px)" },
    {
      filter: "blur(0px)"
    },
    0
  )
  .fromTo(
    "h1",
    { filter: "blur(10px)" },
    {
      filter: "blur(0px)"
    },
    0
  );