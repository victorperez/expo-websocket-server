import { useState } from 'react';
import ExpoWebsocketServer from 'expo-websocket-server';
import { Button, SafeAreaView, ScrollView, Text, View } from 'react-native';

export default function App() {
  const [ipAddress, setIpAddress] = useState('');
  const [isRunning, setIsRunning] = useState(false);

  const checkLocalIpAddress = async () => {
    const ipAddress = ExpoWebsocketServer.getLocalIpAddress();
    setIpAddress(ipAddress);
  };

  const checkIsRunning = async () => {
    const isRunning = ExpoWebsocketServer.isRunning();
    setIsRunning(isRunning);
  };

  return (
    <SafeAreaView style={styles.container}>
      <ScrollView style={styles.container}>
        <Text style={styles.header}>Module API Example</Text>
        <Group name="WebSocket Server Control">
          <Button title="Start Server" onPress={() => ExpoWebsocketServer.start(8888)} />
          <Button title="Stop Server" onPress={() => ExpoWebsocketServer.stop()} />
          <Button title="Get Server IP address" onPress={checkLocalIpAddress} />
          <Text>Local IP address: {ipAddress ? ipAddress : '-'}</Text>
          <Button title="Get Server status" onPress={checkIsRunning} />
          <Text>Is running?: {isRunning ? 'true' : 'false'}</Text>
        </Group>
      </ScrollView>
    </SafeAreaView>
  );
}

function Group(props: { name: string; children: React.ReactNode }) {
  return (
    <View style={styles.group}>
      <Text style={styles.groupHeader}>{props.name}</Text>
      {props.children}
    </View>
  );
}

const styles = {
  header: {
    fontSize: 30,
    margin: 20,
  },
  groupHeader: {
    fontSize: 20,
    marginBottom: 20,
  },
  group: {
    margin: 20,
    backgroundColor: '#fff',
    borderRadius: 10,
    padding: 20,
  },
  container: {
    flex: 1,
    backgroundColor: '#eee',
  },
  view: {
    flex: 1,
    height: 200,
  },
};
