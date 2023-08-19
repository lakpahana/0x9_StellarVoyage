import { StyleSheet} from "react-native";
import Constants from "expo-constants";
import themecolor from "./config/themecolor";
import CommonView from "./screens/CommonView";
import { AuthProvider, useAuth } from "./contexts/auth.context";
import { NavigationContainer } from "@react-navigation/native";
import { createStackNavigator } from "@react-navigation/stack";
import Login from "./screens/Login";
import BottomBar from "./screens/BottomBar";
import Register from "./screens/Register";

const Stack = createStackNavigator();

function App() {

  return (
   <AuthProvider>
      <Layout/>
   </AuthProvider>
  );
}

export const Layout = () => {
  const {authState, onLogout} = useAuth();
  return (
    <NavigationContainer>
      <Stack.Navigator>{authState?.authenticated ? (
        <Stack.Screen name="Main" options={{ headerShown: false }} component={BottomBar} />
      ):(
        <Stack.Screen name="Login" options={{ headerShown: false }} component={Login} />
      )}
      </Stack.Navigator>
    </NavigationContainer>
  );
};

let AppEntryPoint = App;

if (Constants.expoConfig?.extra?.storybookEnabled === "true") {
  AppEntryPoint = require("./.ondevice").default;
}



export default AppEntryPoint;

