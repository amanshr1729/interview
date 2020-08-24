include <iostream>
#include <cstdio>
#include <string>
#include <sstream> 
#include <vector>
#include <set>
#include <map>
#include <queue>
#include <stack>
#include <cmath>
#include <algorithm>
#include <cstring>
#include <ctime>
#include <cassert>
using namespace std;
#define pb push_back
#define mp make_pair
#define pii pair<int,int>
#define vi vector<int>
#define SZ(x) ((int)(x.size()))
#define fi first
#define se second
#define FOR(i,n) for(int (i)=0;(i)<(n);++(i))
#define FORI(i,n) for(int (i)=1;(i)<=(n);++(i))
#define IN(x,y) ((y).find((x))!=(y).end())
#define ALL(t) t.begin(),t.end()
#define FOREACH(i,t) for (typeof(t.begin()) i=t.begin(); i!=t.end(); i++)
#define REP(i,a,b) for(int (i)=(a);(i)<=(b);++i)
#define REPD(i,a,b) for(int (i)=(a); (i)>=(b);--i)
#define REMAX(a,b) (a)=max((a),(b));
#define REMIN(a,b) (a)=min((a),(b));
#define DBG cerr << "debug here" << endl;
#define DBGV(vari) cerr << #vari<< " = "<< (vari) <<endl;
typedef long long ll;
typedef pair<pii, int> EDGE;
#define me(v,u,c) mp(mp(v, u), c)
const int MAXN = 1000000;
int root[MAXN];
int cnt[MAXN];
int Find(int v)
{
    if(root[v] == v) return v;
    int p = Find(root[v]);
    root[v] = p;
    return root[v];
}
bool Union(int v, int u)
{
    int fv = Find(v);
    int fu = Find(u);
    if(fv == fu) return false;
    if(cnt[fv] < cnt[fu])
    {
        cnt[fu] += cnt[fv];
        root[fv] = fu;
    }
    else
    {
        cnt[fv] += cnt[fu];
        root[fu] = fv;
    }
    return true;
}
bool cmp(EDGE a, EDGE b)
{
    return a.se < b.se;
}
int rand_in(int a, int b)
{
    return rand()%(b - a + 1) + a;
}
int main()    
{
    ios_base::sync_with_stdio(0);
    string s;
    int n, m;
    ll k;
    cin >> n >> m >> k;
    FOR(i, n) root[i] = i;
    FOR(i, n) cnt[i] = 1;
    vector<EDGE> edges;
    FOR(i, m)
    {
        int v, u, c;
        cin >> v >> u >> c;
        --v; --u;
        edges.pb(me(v, u, c));
    }
    sort(ALL(edges), cmp);
    int forests = n;
    int res = (forests - 1 <= k) ? forests - 1 : -1;
    ll cost = 0;
    for(int i = 0; i < m && forests > 1; ++i)
    {
        int v = edges[i].fi.fi;
        int u = edges[i].fi.se;
        int c = edges[i].se;
        if(Union(v, u))
        {
            --forests;
            cost += c;
            if(cost + forests - 1 <= k)
            {
                res = forests - 1;
            }
        }
    }
    if(forests > 1) res = -1;
    cout << res << endl;
    return 0;
}
