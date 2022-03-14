using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class MainController : MonoBehaviour
{
  // Start is called before the first frame update
  public GameObject enemyPrefub;
  private int xPlace;
  private int yPlace;
  private int zPlace;
  private int xMin = 10;
  private int yMin = -2;
  private int zMin = -7;
  private int xMax = 17;
  private int yMax = 2;
  private int zMax = 4;
  private float seconds;
  private int count = 0;

  void Start()
  {
  }

  // Update is called once per frame
  void Update()
  {
    //エンターキーが入力された場合「true」
    if (Input.GetKeyDown(KeyCode.Return)){
      SceneManager.LoadScene("SampleScene");
    }
    if (count<=20){

      seconds += Time.deltaTime;
      if (seconds >= 1)
      {
        seconds = 0;
        createEnemy();
        count = count + 1;
      }
    }
  }

  void createEnemy(){
    //生成する場所をランダムに決める
    xPlace = Random.Range (xMin, xMax);
    zPlace = Random.Range (zMin, zMax);
    //enemyをインスタンス化する(生成する)
    GameObject enemy　= Instantiate(enemyPrefub);
    //生成した敵の座標を決定する(現状X=0,Y=10,Z=(ランダム)の位置に出力)
    enemy.transform.position = new Vector3(xPlace,yPlace,zPlace);
  }

  public void PrintEvent()
  {
    Debug.Log("PrintEvent");
  }

}
