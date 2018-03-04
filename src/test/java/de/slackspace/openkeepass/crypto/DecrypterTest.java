package de.slackspace.openkeepass.crypto;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import de.slackspace.openkeepass.crypto.sha.Sha256;
import de.slackspace.openkeepass.domain.CompressionAlgorithm;
import de.slackspace.openkeepass.domain.CrsAlgorithm;
import de.slackspace.openkeepass.domain.KeePassHeader;
import de.slackspace.openkeepass.util.ByteUtils;

public class DecrypterTest {

    @Test
    public void shouldEncryptAndDecryptDatabase() throws IOException {
        String password = "abcdefg";
        byte[] passwordBytes = password.getBytes("UTF-8");
        byte[] hashedPassword = Sha256.getInstance().hash(passwordBytes);

        KeePassHeader keepassHeader = new KeePassHeader();
        keepassHeader.setValue(KeePassHeader.MASTER_SEED, ByteUtils.hexStringToByteArray("35ac8b529bc4f6e44194bccd0537fcb433a30bcb847e63156262c4df99c528ca")); // master
        // seed
        keepassHeader.setValue(KeePassHeader.TRANSFORM_SEED,
                ByteUtils.hexStringToByteArray("0d52d93efc5493ae6623f0d5d69bb76bd976bb717f4ee67abbe43528ebfbb646")); // transform
        // seed
        keepassHeader.setValue(KeePassHeader.TRANSFORM_ROUNDS, ByteUtils.hexStringToByteArray("401F000000000000")); // transform
        // rounds
        keepassHeader.setValue(KeePassHeader.ENCRYPTION_IV, ByteUtils.hexStringToByteArray("2c605455f181fbc9462aefb817852b37")); // encryption
        // IV
        keepassHeader.setCompression(CompressionAlgorithm.Gzip);
        keepassHeader.setCrsAlgorithm(CrsAlgorithm.Salsa20);

        byte[] data = ByteUtils.hexStringToByteArray(
                "69d788d9b01ea1facd1c0bf0187e7d74e4aa07b20d464f3d23d0b2dc2f059ff8000000008e0c5564df5e72b78c7b009b3e4a494e05c0d0387957d246d6b8cc8489e4ba19ad0600001f8b0800000000000400ed5adb6ee248107d66a5fd07c4be6d94d8e616888c4704cce40664b824b3f3b26a4c010ec626dd4d08339a7fdff615dbb48164273b62365114e1ea53d5edf63955d50ef287e799917e024c74cbac64a41331930653b386ba39ae641674745ccaa40945e61019960995cc0a48e683f2fb6ff235c02d22a4a11bc02e53721328b23fa4e48f600246d4c28a879185b5c941d411450344a08566a0f48050df200b91a10d706d82cc310c95ac28e58f25e9389bed89e5b342f92c57fe12f5f591911075201ad6e794ddab72b520348dd204cde606a48783b57b1895e4bedf42380e6e4018a18541fb04b0bdd6b4c03527cc513a2b14ed39f860275013e9260513991a5ce8846dfbaa8e5644c9150bb29030e6f8d52cc3c2fe6a9a8850c0d7b0dabe8e0d18cfb9039a722c6d806d330fdeb0b0063c0777c07581195bfb2db62868c1a34ac99ea1a753c6cb06326c4e456c1198bf7b3164608e806d2a2f2d3c547a78b1c606d668e0ce4d3c26b344202df697c440aecdb93f817b8332dbb29566c0b96eaa261a18e0af66d31e83f7fb9775a5cafda954c2fe0e30e6bc9d029b38c75d35295ef580e90bb1bbfa88adc57ccb0278e8a430db97b3c5c109e8d1be899e2f29cc882289b210b7c5805dfd2b28c56c59ca178a61b06377b0378ca85d30d8a382a1bbf8c6e353f3aab47a30faddaa4acbed59abfb90756e75131b84e859f33b9de8ec01ba03067e5ae256365750a19d3f6ab7c70df5a1b3f483c4d14e18f61810d681f83aaeb14467cdec94e45a6c66b9a95aee5816f57276e09f921d02244f1cf023253b1aa191fc1d928d43666f1529f952b34ce6982fcb82f7d1b5f7f49947796f0b9aacf88c740dd99cb707139e3217ea85a961d8e51e818466af6a1a10c27364bf396fde10c873559fe73a231d773ec9760b01c22eeb0ce05f7aa37d82c650b31626554a6ccfd757fe5a2df7b6b74b218e72b75c08edb97c49d8ccacbe0799246470115ea5a92ea8d55bcda10b8f0bd62240f064dd84e30f2be6c2306c11468c61641710d626acc18840d756171be5b7a3e92dd98387f6e70c3efbd4d607c39bfb5249eb17cf9ff0ed27e3e9fab1598d523be0ab18a56b4a662508c64c2de6305c2d6ddd216dca1d68b3f60aeb436015606deca131095d8544b09f0abc4e638b0ab6cbc0f3e7c860b70ef285241dbc46083b94109182c491c24e2df85bc5d342540c29b94b71404176c91a0fc54963b2607ff4cd77c858803fe05ef8d1220178e1fc7e811331ed157b185632b616334a3f57a6d74f77aa59562b2f9ec9e977780befba3dafa38c1747652ce6c59c503a3f138429c09cdde0896e8e2ce1e5b1bdbe8b37813d967607b7458d669c20e7f8c92ddc1ca5bcd6be8791494680db83d182b81cb1659f34e4bb5609b1343d624bc9f7ba39b4964a0fe131d0b47b250b9e3540b1bb23145bd320972adffa5db5d3aa36d5efdf7ad5f3efdf6eabddee7dbb53f72ed5564fed7c77b625e6e8af46d85c0eb34537c36f6882cc2d6c4f9417a7b9d1d7bb1be1a2bf58156ba3ac06bdc7cbf17ba2dc3f5196fe4f89f24599edbe51ec5fa1e51b65b5f41fd9b74c6c1330e6c274c07ec5b298178b7fdb3df0c8c2b393099d193f34ed35756d82c0c865a583c87b6c1fd24cf8b3f471da7bf9f3e71ee92fd0f78fc863a1a38c97c7924f62e13ce69c5cdc175546f81c133bc8ac4f32a578c63bace4947c9a79ebe4947bebe4b4f34cb3f350f38253cd8b8e35fc73cde339993db5af6735f1b1b3babc6aa3ac5eed6c3fd744ebb547f4e43821a22756ece4929d5cb3f9453b56b563cad82d0d493c13c5b3acb8431abbb451282468639b3862736f8ae355ead8258fddc57b0f8138f7cb17484c21f1329474d2f1ab50f465b9c970d16a142f47fcf8dc0621b14380c9d174727ff757eb34f7b1599586a57151fa5cea4f8ab569e535b36f360e418d5d718e42fb058d370db1ae616c5963034e346bf6aae0bcbe60bd687b7857d8785bb0a32ff8578d41bc4c6fd4e958a116c22f1d37ab36fe4c4feb476a53101fef253cbe3dcf8f0be6925bb5dd0683ec53b573875eb5a55ff648716855fb356f237770bebe1c56d1b599a7eaea73c3e8178a42b67ffe89cbf91650964ba77b71feb0291fccfc9f535e7ca7fc9b53bed61ef7f12d2ddc8ac5d3621d565f4c012df894bf34296013e83e9c970e9bf385eccfe27cf69df36fce79e1f97ed41e99b5e2543f2d92d6047fe99c627e6b034da4eff53a422a1f36e3dfb3fcafccf8cef08166a52b0389476ae1535935ae2e96d2c6bf121c965f5833182073ca16b95773737ad8bcff7999febda1ffb1bc8f7cae83011486edc1036894f8dfbcf1be70631fe8d7df9efc076926aea08429000001000000000000000000000000000000000000000000000000000000000000000000000000000000");

        CryptoInformation cryptoInformation = new CryptoInformation(KeePassHeader.VERSION_SIGNATURE_LENGTH, keepassHeader.getMasterSeed(),
                keepassHeader.getTransformSeed(), keepassHeader.getEncryptionIV(), keepassHeader.getTransformRounds(), keepassHeader.getHeaderSize());
        byte[] encryptedDatabase = new Decrypter().encryptDatabase(hashedPassword, cryptoInformation, data);
        byte[] decryptedDatabase = new Decrypter().decryptDatabase(hashedPassword, cryptoInformation, encryptedDatabase);

        Assert.assertArrayEquals(data, decryptedDatabase);
    }
}
