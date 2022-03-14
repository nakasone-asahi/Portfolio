using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GunController : MonoBehaviour
{
    // Start is called before the first frame update
    //弾丸オブジェクト
    public GameObject bullet;
    public GameObject BulletPosition;
    public float speed = 1000;

    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {
      //スペースーキーが入力された場合「true」
      if (Input.GetKeyDown(KeyCode.Space)){
        //弾をインスタンス化して装填
            GameObject createdBullet = Instantiate(bullet) as GameObject;
            createdBullet.transform.position = BulletPosition.transform.position;

            //発射ベクトル
            Vector3 force;
            //発射の向きと速度を決定
            force = BulletPosition.transform.forward * speed;
            // Rigidbodyに力を加えて発射
            createdBullet.GetComponent<Rigidbody>().AddForce(force);
      }

    }
}
