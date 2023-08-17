import React from "react";
import { TouchableOpacity, Text, StyleSheet,Image } from "react-native";
import { IconButtonModel } from "../../Models/Button";
import color from "../../config/color";


export const IconButton = ({ onPress, text, icon }: IconButtonModel) => {
  return (
    <TouchableOpacity style={styles.container} onPress={onPress}>
        <Image style={styles.image} source={icon}/>
        <Text style={styles.text}>{text}</Text>
    </TouchableOpacity>
  );
};

const styles = StyleSheet.create({
  container: {
    paddingHorizontal: 42,
    width:"90%",
    paddingVertical: 8,
    backgroundColor: color.deactiveButton,
    alignItems: "center",
    justifyContent: "space-between",
    borderRadius:12,
    height:42,
    display:"flex",
    flexDirection:"row"
  },
  image:{
    height:32,
    width:32
    },
  text: { color: "black",fontFamily:"Monserrata", fontSize: 16,fontWeight:"400" },
});
