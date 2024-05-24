import React from "react";
import {ComponentPreview, Previews} from "@react-buddy/ide-toolbox";
import {PaletteTree} from "./palette";
import {Post} from "../scenes/Post";
import {CalendarPage} from "../scenes/Calendar";

const ComponentPreviews = () => {
  return (
      <Previews palette={<PaletteTree/>}>
        <ComponentPreview path="/ComponentPreviews">
          <ComponentPreviews/>
        </ComponentPreview>
        <ComponentPreview path="/Post">
          <Post/>
        </ComponentPreview>
        <ComponentPreview path="/CalendarPage">
          <CalendarPage/>
        </ComponentPreview>
      </Previews>
  );
};

export default ComponentPreviews;